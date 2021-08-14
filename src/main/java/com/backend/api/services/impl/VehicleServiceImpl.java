package com.backend.api.services.impl;

import com.backend.api.configs.mapper.DataMapper;
import com.backend.api.domain.User;
import com.backend.api.domain.Vehicle;
import com.backend.api.dtos.crud.create.CreateVehicleDto;
import com.backend.api.dtos.crud.update.UpdateVehicleDto;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.repositories.VehicleRepository;
import com.backend.api.security.UserSS;
import com.backend.api.services.UserService;
import com.backend.api.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

  private final VehicleRepository vehicleRepository;
  private final DataMapper mapper;
  private final UserService userService;

  @Override
  public Vehicle create(CreateVehicleDto createVehicleDto) {
    UserSS userSS = UserSSServiceImpl.authenticated();
    User user = userService.findById(userSS.getId());

    Vehicle vehicle = toEntity(createVehicleDto);
    vehicle.setCustomer(user.getCustomer());

    vehicleRepository.save(vehicle);
    return vehicle;
  }

  @Override
  public void delete(Long id) {
    Vehicle vehicle = findById(id);
    vehicleRepository.delete(vehicle);
  }

  @Override
  public Vehicle findById(Long id) {
    return vehicleRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Vehicle not found", "Could not find this vehicle."));
  }

  @Override
  public void update(Long id, UpdateVehicleDto updateVehicleDto) {
    Vehicle vehicle = findById(id);
    vehicle.update(updateVehicleDto);
    vehicleRepository.save(vehicle);
  }

  @Override
  public List<Vehicle> findAll() {
    return vehicleRepository.findAll();
  }

  @Override
  public Vehicle toEntity(CreateVehicleDto createVehicleDto) {
    return mapper.mapTo(createVehicleDto, Vehicle.class);
  }
}
