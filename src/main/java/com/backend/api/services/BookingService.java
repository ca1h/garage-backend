package com.backend.api.services;

import com.backend.api.domain.Booking;
import com.backend.api.domain.enums.BookingStatus;
import com.backend.api.dtos.crud.create.CreateBookingDto;
import com.backend.api.dtos.crud.read.ReadBookingDto;
import com.backend.api.dtos.crud.update.UpdateBookingDto;

import java.time.LocalDate;
import java.util.List;

public interface BookingService extends CrudService<Booking, CreateBookingDto, ReadBookingDto, UpdateBookingDto> {
  Booking findLastBooking();

  void updateBookingStatus(Long bookingId, BookingStatus bookingStatus);

  void addSupplies(Long bookingId, List<Long> suppliesIds);

  void addComment(Long bookingId, String comment);

  void linkEmployee(Long bookingId, Long employeeId);

  Long countBookingsByDate(LocalDate date);
}
