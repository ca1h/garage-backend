package com.backend.api.dtos.crud.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateEmployeeDto {
  private Long userId;
  private String name;
}
