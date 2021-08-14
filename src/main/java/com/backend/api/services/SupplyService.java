package com.backend.api.services;

import com.backend.api.domain.Supply;
import com.backend.api.dtos.crud.create.CreateSupplyDto;
import com.backend.api.dtos.crud.read.ReadSupplyDto;
import com.backend.api.dtos.crud.update.UpdateSupplyDto;

public interface SupplyService extends CrudService<Supply, CreateSupplyDto, ReadSupplyDto, UpdateSupplyDto> {
}
