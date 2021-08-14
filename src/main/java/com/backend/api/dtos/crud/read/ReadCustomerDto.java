package com.backend.api.dtos.crud.read;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReadCustomerDto {
  @NotNull
  private String name;

  @NotNull
  private String phoneNumber;

  private ReadVehicleDto vehicle;
}
