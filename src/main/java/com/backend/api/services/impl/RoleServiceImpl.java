package com.backend.api.services.impl;

import com.backend.api.domain.Role;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.repositories.RoleRepository;
import com.backend.api.services.RoleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  @Override
  public Role findById(Long id) {
    return roleRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Role not found", "Could not find this role"));
  }

  @Override
  public Role findByName(String name) {
    return roleRepository.findByName(name)
        .orElseThrow(() -> new ObjectNotFoundException("Role not found", "Could not find this role"));
  }
}
