package com.backend.api.dtos.crud.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateCustomerDto {
  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  @NotEmpty
  private String phoneNumber;

  @NotNull
  @Email
  @NotEmpty
  private String email;

  @NotNull
  @NotEmpty
  @Pattern(message = "At least 6 letters at max 20 letters", regexp = ".{6,20}")
  private String password;

  public CreateUserDto getUser() {
    return CreateUserDto
        .builder()
        .email(email)
        .password(password)
        .build();
  }
}
