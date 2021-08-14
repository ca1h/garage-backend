package com.backend.api.dtos.crud.read;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReadSupplyDto {
  private Long id;
  private String name;
  private Float price;
}
