package com.backend.api.controllers;


import com.backend.api.configs.mapper.DataMapper;
import com.backend.api.domain.Base;
import com.backend.api.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CrudController<Entity extends Base, CreateDto, ReadDto, UpdateDto> {

  protected final CrudService<Entity, CreateDto, ReadDto, UpdateDto> service;
  private final Class<ReadDto> readDtoClass;
  @Autowired
  protected DataMapper mapper;

  //https://xebia.com/blog/acessing-generic-types-at-runtime-in-java/
  public CrudController(CrudService service) {
    this.readDtoClass = (Class<ReadDto>) ((ParameterizedType) this.getClass().getGenericSuperclass())
        .getActualTypeArguments()[2];
    this.service = service;
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ReadDto> find(@PathVariable Long id) {
    Entity entity = service.findById(id);
    ReadDto dto = mapper.mapTo(entity, readDtoClass);
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<ReadDto>> listAll() {
    List<Entity> list = service.findAll();

    List<ReadDto> listDto = mapper.mapAllTo(list, readDtoClass);
    return ResponseEntity.ok().body(listDto);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> insert(@Valid @RequestBody CreateDto createDto) {
    final var entity = service.create(createDto);
    return ResponseEntity.created(createLocation(entity.getId()).toUri()).build();
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable("id") Long id, @Valid @RequestBody UpdateDto updateDto) {
    service.update(id, updateDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long id) {
    service.delete(id);
  }

  protected UriComponents createLocation(Long id) {
    ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
    return builder.path("/").path(String.valueOf(id)).build();
  }

}