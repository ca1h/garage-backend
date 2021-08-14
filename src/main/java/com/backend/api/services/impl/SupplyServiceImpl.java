package com.backend.api.services.impl;

import com.backend.api.domain.Supply;
import com.backend.api.dtos.crud.create.CreateSupplyDto;
import com.backend.api.dtos.crud.update.UpdateSupplyDto;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.repositories.SupplyRepository;
import com.backend.api.services.SupplyService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SupplyServiceImpl implements SupplyService {
  private final SupplyRepository supplyRepository;

  @Override
  public Supply create(CreateSupplyDto createSupplyDto) {
    Supply supply = toEntity(createSupplyDto);
    return supplyRepository.save(supply);
  }

  @Override
  public void delete(Long id) {
    Supply supply = findById(id);
    supplyRepository.delete(supply);
  }

  @Override
  public Supply findById(Long id) {
    return supplyRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Supply not found", "Could not find this supply"));
  }

  @Override
  public void update(Long id, UpdateSupplyDto updateSupplyDto) {
    Supply supply = findById(id);
    supply.update(updateSupplyDto);
  }

  @Override
  public List<Supply> findAll() {
    return supplyRepository.findAll();
  }

  @Override
  public Supply toEntity(CreateSupplyDto createSupplyDto) {
    return Supply
        .builder()
        .name(createSupplyDto.getName())
        .price(createSupplyDto.getPrice())
        .build();
  }
}
