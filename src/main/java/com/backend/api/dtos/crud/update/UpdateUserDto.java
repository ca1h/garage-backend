    package com.backend.api.dtos.crud.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UpdateUserDto {
  private String phoneNumber;

  private String password;

  private Long role;

  private String name;
}
