package com.backend.api.services;

import com.backend.api.domain.Customer;
import com.backend.api.dtos.crud.create.CreateCustomerDto;
import com.backend.api.dtos.crud.read.ReadCustomerDto;
import com.backend.api.dtos.crud.update.UpdateCustomerDto;

public interface CustomerService extends CrudService<Customer, CreateCustomerDto, ReadCustomerDto, UpdateCustomerDto> {
}
