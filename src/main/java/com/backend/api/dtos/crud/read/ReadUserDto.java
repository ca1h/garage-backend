package com.backend.api.dtos.crud.read;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReadUserDto {
  private Long id;
  private String email;
  private ReadCustomerDto customer;

}
