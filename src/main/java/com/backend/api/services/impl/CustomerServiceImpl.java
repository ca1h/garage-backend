package com.backend.api.services.impl;

import com.backend.api.domain.Customer;
import com.backend.api.domain.User;
import com.backend.api.dtos.crud.create.CreateCustomerDto;
import com.backend.api.dtos.crud.update.UpdateCustomerDto;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.repositories.CustomerRepository;
import com.backend.api.services.CustomerService;
import com.backend.api.services.RoleService;
import com.backend.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final UserService userService;
  private final RoleService roleService;

  @Override
  public Customer create(CreateCustomerDto createCustomerDto) {
    Customer customer = toEntity(createCustomerDto);
    return customerRepository.save(customer);
  }

  @Override
  public void delete(Long id) {
    Customer customer = findById(id);
    customerRepository.delete(customer);
  }

  @Override
  public Customer findById(Long id) {
    return customerRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Customer not found", "Could not find this customer."));
  }

  @Override
  public void update(Long id, UpdateCustomerDto updateCustomerDto) {
    Customer customer = findById(id);
    customer.update(updateCustomerDto);
    customerRepository.save(customer);
  }

  @Override
  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  @Override
  public Customer toEntity(CreateCustomerDto createCustomerDto) {
    User user = userService.create(createCustomerDto.getUser());
    user.setRole(roleService.findByName("ROLE_CUSTOMER"));
    return Customer
        .builder()
        .name(createCustomerDto.getName())
        .phoneNumber(createCustomerDto.getPhoneNumber())
        .user(user)
        .build();
  }
}
