package com.backend.api.controllers;

import com.backend.api.configs.mapper.DataMapper;
import com.backend.api.domain.User;
import com.backend.api.dtos.AuthUserDto;
import com.backend.api.dtos.crud.create.CreateUserDto;
import com.backend.api.dtos.crud.read.ReadSimpleUserDataDto;
import com.backend.api.dtos.crud.read.ReadUserDto;
import com.backend.api.dtos.crud.update.UpdateUserDto;
import com.backend.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController extends CrudController<User, CreateUserDto, ReadSimpleUserDataDto, UpdateUserDto> {

  private UserService userService;

  @Autowired
  private DataMapper mapper;

  public UserController(UserService userService) {
    super(userService);
    this.userService = userService;
  }

  @GetMapping("/logged")
  public ReadUserDto getLoggedInUserData() {
    return userService.getLoggedInUserData();
  }

  @PutMapping
  public void updateLoggedInUser(@Valid @RequestBody UpdateUserDto updateUserDto) {
    userService.updateLoggedInUser(updateUserDto);
  }

  @GetMapping("/auth")
  public AuthUserDto auth() {
    return mapper.mapTo(userService.getLoggedInUser(), AuthUserDto.class);
  }

}
