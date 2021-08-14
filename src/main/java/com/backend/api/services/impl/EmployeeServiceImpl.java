package com.backend.api.services.impl;

import com.backend.api.domain.Employee;
import com.backend.api.domain.User;
import com.backend.api.dtos.crud.create.CreateEmployeeDto;
import com.backend.api.dtos.crud.update.UpdateEmployeeDto;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.repositories.EmployeeRepository;
import com.backend.api.services.EmployeeService;
import com.backend.api.services.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;


  @Autowired
  @Lazy
  private UserService userService;

  @Override
  public Employee create(CreateEmployeeDto createEmployeeDto) {
    Employee employee = toEntity(createEmployeeDto);
    return employeeRepository.save(employee);
  }

  @Override
  public void delete(Long id) {
    Employee employee = findById(id);
    employeeRepository.delete(employee);
  }

  @Override
  public Employee findById(Long id) {
    return employeeRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Employee not found", "Could not find this employee"));
  }

  @Override
  public void update(Long id, UpdateEmployeeDto updateEmployeeDto) {
    Employee employee = findById(id);
    employee.update(updateEmployeeDto);
  }

  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee toEntity(CreateEmployeeDto createEmployeeDto) {
    User user = userService.findById(createEmployeeDto.getUserId());
    return Employee
        .builder()
        .name(createEmployeeDto.getName())
        .user(user)
        .build();
  }
}
