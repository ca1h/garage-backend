package com.backend.api.controllers;

import com.backend.api.configs.mapper.DataMapper;
import com.backend.api.domain.Booking;
import com.backend.api.dtos.AddCommentToBookingsDto;
import com.backend.api.dtos.AddSuppliesToBookingsDto;
import com.backend.api.dtos.UpdateBookingStatusDto;
import com.backend.api.dtos.crud.create.CreateBookingDto;
import com.backend.api.dtos.crud.read.ReadBookingDto;
import com.backend.api.dtos.crud.update.UpdateBookingDto;
import com.backend.api.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/booking")
public class BookingController extends CrudController<Booking, CreateBookingDto, ReadBookingDto, UpdateBookingDto> {

  private final BookingService bookingService;

  @Autowired
  private DataMapper mapper;

  public BookingController(BookingService bookingService) {
    super(bookingService);
    this.bookingService = bookingService;
  }

  @GetMapping("/last-booking")
  public ResponseEntity<ReadBookingDto> findLastBooking() {
    ReadBookingDto dto = mapper.mapTo(bookingService.findLastBooking(), ReadBookingDto.class);
    return ResponseEntity.ok().body(dto);
  }

  @PatchMapping("/{id}/booking-status")
  public void updateBookingStatus(@PathVariable("id") Long id, @Valid @RequestBody UpdateBookingStatusDto updateBookingStatusDto) {
    bookingService.updateBookingStatus(id, updateBookingStatusDto.getBookingStatus());
  }

  @PatchMapping("/{id}/add-supplies")
  public void addSupplies(@PathVariable("id") Long id,@Valid @RequestBody AddSuppliesToBookingsDto addSuppliesToBookingsDto) {
    bookingService.addSupplies(id, addSuppliesToBookingsDto.getIds());
  }

  @PatchMapping("/{id}/add-comment")
  public void addComment(@PathVariable("id") Long id,@Valid @RequestBody AddCommentToBookingsDto addCommentToBookingsDto) {
    bookingService.addComment(id, addCommentToBookingsDto.getComment());
  }

  @PatchMapping("/{id}/link-employee")
  public void linkEmployee(@PathVariable("id") Long id, @RequestParam Long employeeId) {
    bookingService.linkEmployee(id, employeeId);
  }

}
