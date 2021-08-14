package com.backend.api.dtos.crud.read;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class ReadCredentialsDto implements Serializable {
  private Long id;
  private String email;
  private String password;
}
