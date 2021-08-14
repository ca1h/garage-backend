package com.backend.api.dtos.crud.create;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class CreateCredentialsDto implements Serializable {
  private String email;
  private String password;
}
