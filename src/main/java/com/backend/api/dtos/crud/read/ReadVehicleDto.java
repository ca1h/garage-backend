package com.backend.api.dtos.crud.read;

import com.backend.api.enums.EngineType;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class ReadVehicleDto implements Serializable {
  private Long id;
  private String type;
  private String make;
  private String licenceDetails;
  private EngineType engineType;
}
