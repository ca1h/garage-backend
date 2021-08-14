package com.backend.api.controllers;

import com.backend.api.domain.Employee;
import com.backend.api.dtos.crud.create.CreateEmployeeDto;
import com.backend.api.dtos.crud.read.ReadEmployeeDto;
import com.backend.api.dtos.crud.update.UpdateEmployeeDto;
import com.backend.api.services.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController extends CrudController<Employee, CreateEmployeeDto, ReadEmployeeDto, UpdateEmployeeDto> {

  private EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    super(employeeService);
    this.employeeService = employeeService;
  }

}
