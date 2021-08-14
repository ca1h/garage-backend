package com.backend.api.dtos.crud.update;

import com.backend.api.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Builder
public class UpdateVehicleDto implements Serializable {
  private String type;
  private String make;
  private String licenceDetails;
  private EngineType engineType;
}
