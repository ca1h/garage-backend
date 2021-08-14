package com.backend.api.dtos.crud.read;

import com.backend.api.domain.enums.BookingStatus;
import com.backend.api.domain.enums.BookingType;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReadBookingDto {
  private BookingType type;
  private LocalDate date;
  private BookingStatus status;
  private Float total;
  private List<String> comments;
  private List<ReadSupplyDto> supplies;
  private Long id;
  private ReadCustomerDto customer;
  private Long employeeId;
  private String employeeName;
}
