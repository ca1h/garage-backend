package com.backend.api.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReadRoleDto {
  @NotNull
  Long id;
  String name;
}
