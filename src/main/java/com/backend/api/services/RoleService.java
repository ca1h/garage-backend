package com.backend.api.services;

import com.backend.api.domain.Role;

import java.util.List;

public interface RoleService {
  List<Role> findAll();
  Role findById(Long id);
  Role findByName(String name);
}
