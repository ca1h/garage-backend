package com.backend.api.dtos.crud.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Builder
public class UpdateCustomerDto {
  @NotNull
  private String phoneNumber;

  @NotNull
  private String name;
}
