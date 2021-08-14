package com.backend.api.dtos;

import com.backend.api.domain.enums.BookingStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class UpdateBookingStatusDto {
  BookingStatus bookingStatus;
}
