package com.backend.api.services;

import java.util.List;

public interface CrudService<Entity, CreateDto, ReadDto, UpdateDto> {
  Entity create(CreateDto createDto);

  void delete(Long id);

  Entity findById(Long id);

  void update(Long id, UpdateDto updateDto);

  List<Entity> findAll();

  Entity toEntity(CreateDto createDto);

}
