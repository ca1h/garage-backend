package com.backend.api.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class AddSuppliesToBookingsDto {
  List<Long> ids;
}
