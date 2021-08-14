package com.backend.api.controllers;

import com.backend.api.configs.mapper.DataMapper;
import com.backend.api.dtos.ReadRoleDto;
import com.backend.api.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/role")
@AllArgsConstructor
public class RoleController {
  private final DataMapper mapper;
  private final RoleService roleService;

  @GetMapping
  public List<ReadRoleDto> findAll() {
    return mapper.mapAllTo(roleService.findAll(), ReadRoleDto.class);
  }
}
