package com.backend.api.dtos.crud.read;

import com.backend.api.dtos.ReadRoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReadSimpleUserDataDto {
  private Long id;
  private String email;
  private ReadRoleDto role;
  private String employeeName;
  private String customerName;
}
