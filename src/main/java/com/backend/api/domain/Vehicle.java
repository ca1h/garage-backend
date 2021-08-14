package com.backend.api.domain;

import com.backend.api.dtos.crud.update.UpdateVehicleDto;
import com.backend.api.enums.EngineType;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Vehicle extends Base {
  private String type;
  private String make;
  private String licenceDetails;

  @OneToOne
  private Customer customer;


  @Column(name = "engine_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private EngineType engineType;

  public void update(UpdateVehicleDto dto) {
    type = dto.getType();
    make = dto.getMake();
    licenceDetails = dto.getLicenceDetails();
    engineType = dto.getEngineType();
  }
}
