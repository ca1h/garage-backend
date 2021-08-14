package com.backend.api.services;

import com.backend.api.domain.Vehicle;
import com.backend.api.dtos.crud.create.CreateVehicleDto;
import com.backend.api.dtos.crud.read.ReadVehicleDto;
import com.backend.api.dtos.crud.update.UpdateVehicleDto;

public interface VehicleService extends CrudService<Vehicle, CreateVehicleDto, ReadVehicleDto, UpdateVehicleDto> {
}
