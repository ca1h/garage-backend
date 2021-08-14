package com.backend.api.dtos.crud.create;

import com.backend.api.domain.enums.BookingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateBookingDto {
  private LocalDate date;
  private BookingType bookingType;
  private String description;
}
