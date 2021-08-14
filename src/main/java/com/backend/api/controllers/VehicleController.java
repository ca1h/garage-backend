package com.backend.api.controllers;

import com.backend.api.domain.Vehicle;
import com.backend.api.dtos.crud.create.CreateVehicleDto;
import com.backend.api.dtos.crud.read.ReadVehicleDto;
import com.backend.api.dtos.crud.update.UpdateVehicleDto;
import com.backend.api.services.VehicleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController extends CrudController<Vehicle, CreateVehicleDto, ReadVehicleDto, UpdateVehicleDto> {

  private VehicleService vehicleService;

  public VehicleController(VehicleService vehicleService) {
    super(vehicleService);
    this.vehicleService = vehicleService;
  }

}
