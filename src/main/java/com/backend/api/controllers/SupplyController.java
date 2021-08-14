package com.backend.api.controllers;

import com.backend.api.domain.Supply;
import com.backend.api.dtos.crud.create.CreateSupplyDto;
import com.backend.api.dtos.crud.read.ReadSupplyDto;
import com.backend.api.dtos.crud.update.UpdateSupplyDto;
import com.backend.api.services.SupplyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/supply")
public class SupplyController extends CrudController<Supply, CreateSupplyDto, ReadSupplyDto, UpdateSupplyDto> {

  private SupplyService supplyService;

  public SupplyController(SupplyService supplyService) {
    super(supplyService);
    this.supplyService = supplyService;
  }

}
