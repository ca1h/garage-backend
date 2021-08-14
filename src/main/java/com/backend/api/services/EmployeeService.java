package com.backend.api.services;

import com.backend.api.domain.Employee;
import com.backend.api.dtos.crud.create.CreateEmployeeDto;
import com.backend.api.dtos.crud.read.ReadEmployeeDto;
import com.backend.api.dtos.crud.update.UpdateEmployeeDto;

public interface EmployeeService extends CrudService<Employee, CreateEmployeeDto, ReadEmployeeDto, UpdateEmployeeDto> {
}
