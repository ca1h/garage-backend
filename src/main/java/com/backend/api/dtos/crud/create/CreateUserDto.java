package com.backend.api.dtos.crud.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Getter
@Builder
public class CreateUserDto {
  @NotNull
  @Email
  @NotEmpty
  private String email;

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  @NotEmpty
  @Pattern(message = "At least 6 letters at max 20 letters", regexp = ".{6,20}")
  private String password;

  private Long role;


}