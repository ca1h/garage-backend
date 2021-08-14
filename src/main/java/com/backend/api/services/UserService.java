package com.backend.api.services;

import com.backend.api.domain.User;
import com.backend.api.dtos.crud.create.CreateUserDto;
import com.backend.api.dtos.crud.read.ReadUserDto;
import com.backend.api.dtos.crud.update.UpdateUserDto;

import java.util.List;

public interface UserService extends CrudService<User, CreateUserDto, ReadUserDto, UpdateUserDto> {
  User getLoggedInUser();

  ReadUserDto getLoggedInUserData();

  void updateLoggedInUser(UpdateUserDto updateUserDto);
}
