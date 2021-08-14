package com.backend.api.controllers;

import com.backend.api.domain.Customer;
import com.backend.api.dtos.crud.create.CreateCustomerDto;
import com.backend.api.dtos.crud.read.ReadCustomerDto;
import com.backend.api.dtos.crud.update.UpdateCustomerDto;
import com.backend.api.services.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController extends CrudController<Customer, CreateCustomerDto, ReadCustomerDto, UpdateCustomerDto> {

  private CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    super(customerService);
    this.customerService = customerService;
  }

}
