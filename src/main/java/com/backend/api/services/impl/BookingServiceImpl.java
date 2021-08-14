package com.backend.api.services.impl;

import com.backend.api.domain.Booking;
import com.backend.api.domain.Employee;
import com.backend.api.domain.Supply;
import com.backend.api.domain.User;
import com.backend.api.domain.enums.BookingStatus;
import com.backend.api.domain.enums.BookingType;
import com.backend.api.dtos.crud.create.CreateBookingDto;
import com.backend.api.dtos.crud.update.UpdateBookingDto;
import com.backend.api.exceptions.ImpossibleToScheduleException;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.repositories.BookingRepository;
import com.backend.api.repositories.SupplyRepository;
import com.backend.api.services.BookingService;
import com.backend.api.services.EmployeeService;
import com.backend.api.services.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BookingServiceImpl implements BookingService {
  private final UserService userService;
  private final EmployeeService employeeService;

  private final BookingRepository bookingRepository;
  private final SupplyRepository supplyRepository;

  @Override
  public Booking create(CreateBookingDto createBookingDto) {
    // Check if its Sunday
    if (createBookingDto.getDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
      throw new ImpossibleToScheduleException("Sunday", "We don't work at Sundays.");
    }

    // Check if the date is before the curent day
    if (createBookingDto.getDate().isBefore(LocalDate.now())) {
      throw new ImpossibleToScheduleException( "It doesn't make sense.", "Before today");
    }

    User user = userService.getLoggedInUser();

    // Check if the customer has a vehicle registered
    if (user.getCustomer().getVehicle() == null) {
      throw new ImpossibleToScheduleException("You don't have a car registered in our system, please, check your \"My account\" page", "Warning");
    }

    // Check if the booking is concluded ( only one book at a time)
    Booking lastBooking = bookingRepository.findTop1ByCustomerOrderByIdDesc(user.getCustomer()).stream().findFirst().orElse(null);
    if (lastBooking != null && !lastBooking.getBookingStatus().equals(BookingStatus.UNREPAIRABLE) && !lastBooking.getBookingStatus().equals(BookingStatus.FIXED)) {
      throw new ImpossibleToScheduleException("You already have a booking in progress, see your bookings page", "Warning");
    }

    // How many booking have (max 16 per day)
    Long usedBookings = countBookingsByDate(createBookingDto.getDate());

    // Verificar se é uma Major Repair para contar o dobro
    Long multiplier = createBookingDto.getBookingType().equals(BookingType.MAJOR_REPAIR) ? 2L : 1L;

    // Check if there's a slot available for the day selected by the client
    if (usedBookings + multiplier <= 16) {
      Booking booking = toEntity(createBookingDto);
      return bookingRepository.save(booking);
    } else {
      throw new ImpossibleToScheduleException("Full schedule", "No more free space in our schedule for this day.");
    }
  }

  @Override
  public void delete(Long id) {
    Booking booking = findById(id);
    bookingRepository.delete(booking);
  }

  @Override
  public Booking findById(Long id) {
    return bookingRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Booking not found", "Could not find this booking"));
  }

  @Override
  public void update(Long id, UpdateBookingDto updateBookingDto) {
    Booking booking = findById(id);
    booking.update(updateBookingDto);
  }

  @Override
  public List<Booking> findAll() {
    return bookingRepository.findAll();
  }

  @Override
  public Booking findLastBooking() {
    User user = userService.getLoggedInUser();
    return bookingRepository.findTop1ByCustomerOrderByIdDesc(user.getCustomer()).stream().findFirst()
        .orElseThrow(() -> new ObjectNotFoundException("Booking not found", "Could not find this booking"));
  }

  @Override
  public Booking toEntity(CreateBookingDto createBookingDto) {
    User user = userService.getLoggedInUser();

    return Booking
        .builder()
        .customer(user.getCustomer())
        .bookingStatus(BookingStatus.WAITING_CONFIRMATION)
        .bookingType(createBookingDto.getBookingType())
        .description(createBookingDto.getDescription())
        .date(createBookingDto.getDate())
        .total(createBookingDto.getBookingType().getMinPrice())
        .build();
  }

  @Override
  public void updateBookingStatus(Long bookingId, BookingStatus bookingStatus) {
    Booking booking = findById(bookingId);
    booking.setBookingStatus(bookingStatus);
    bookingRepository.save(booking);
  }

  @Override
  public void addSupplies(Long bookingId, List<Long> suppliesIds) {
    Booking booking = findById(bookingId);
    List<Supply> supplies = supplyRepository.findAllById(suppliesIds);
    booking.getSupplies().addAll(supplies);
    Float total = booking.getTotal();
    for(Supply supply : supplies) {
      total += supply.getPrice();
    }
    booking.setTotal(total);
    bookingRepository.save(booking);
  }

  @Override
  public void addComment(Long bookingId, String comment) {
    Booking booking = findById(bookingId);
    booking.getComments().add(comment);
    bookingRepository.save(booking);
  }

  @Override
  public void linkEmployee(Long bookingId, Long employeeId) {
    Booking booking = findById(bookingId);
    Employee employee = employeeService.findById(employeeId);

    booking.setEmployee(employee);
    bookingRepository.save(booking);
  }

  //Check if its a MAJOR_REPAIR and count it as a double booking
  @Override
  public Long countBookingsByDate(LocalDate date) {
    Long count = bookingRepository.findAllByDate(date).stream().reduce(0L, (subtotal, booking) -> {
          if (booking.getBookingType().equals(BookingType.MAJOR_REPAIR)) {
            return subtotal + 2L;
          } else {
            return subtotal + 1L;
          }
        }, Long::sum
    );
    return count;
  }
}
