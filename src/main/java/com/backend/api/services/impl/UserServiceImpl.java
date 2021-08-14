package com.backend.api.services.impl;

import com.backend.api.configs.mapper.DataMapper;
import com.backend.api.domain.Customer;
import com.backend.api.domain.Role;
import com.backend.api.domain.User;
import com.backend.api.domain.Vehicle;
import com.backend.api.dtos.crud.create.CreateEmployeeDto;
import com.backend.api.dtos.crud.create.CreateUserDto;
import com.backend.api.dtos.crud.read.ReadCustomerDto;
import com.backend.api.dtos.crud.read.ReadUserDto;
import com.backend.api.dtos.crud.read.ReadVehicleDto;
import com.backend.api.dtos.crud.update.UpdateUserDto;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.repositories.UserRepository;
import com.backend.api.security.UserSS;
import com.backend.api.services.EmployeeService;
import com.backend.api.services.RoleService;
import com.backend.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder pe;
    @Autowired
    private DataMapper mapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    @Lazy
    private EmployeeService employeeService;

    @Override
    public User create(CreateUserDto createUserDto) {
        User user = toEntity(createUserDto);
        userRepository.save(user);
        verifyIfNeedToCreateEmployee(createUserDto.getName(), user);
        return user;
    }

    private void verifyIfNeedToCreateEmployee(String name, User user) {
        if (user.getRole().getName().equals("ROLE_STAFF") && user.getEmployee() == null) {
            employeeService.create(
                    CreateEmployeeDto
                            .builder()
                            .userId(user.getId())
                            .name(name)
                            .build()
            );
        }
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found", "Could not find this user"));
    }

    @Override
    public void update(Long id, UpdateUserDto updateUserDto) {
        User user = findById(id);
        updateUser(user, updateUserDto);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User toEntity(CreateUserDto createUserDto) {
        Role admin = roleService.findByName("ROLE_ADMIN");
        Role role;
        User logged = getLoggedInUser();
        if (logged != null && getLoggedInUser().getRole().equals(admin)) {
            role = roleService.findById(createUserDto.getRole());
        } else {
            role = roleService.findByName("ROLE_CUSTOMER");
        }

        return User.builder()
                .email(createUserDto.getEmail())
                .role(role)
                .password(pe.encode(createUserDto.getPassword()))
                .build();
    }

    @Override
    public User getLoggedInUser() {
        UserSS user = UserSSServiceImpl.authenticated();
        if (user != null) {
            return findById(user.getId());
        } else {
            return null;
        }
    }

    @Override
    public ReadUserDto getLoggedInUserData() {
        User user = getLoggedInUser();
        Customer customer = user.getCustomer();
        Vehicle vehicle = customer.getVehicle() != null ? customer.getVehicle() : Vehicle.builder().build();
        return ReadUserDto
                .builder()
                .customer(ReadCustomerDto
                        .builder()
                        .name(customer.getName())
                        .phoneNumber(customer.getPhoneNumber())
                        .vehicle(mapper.mapTo(vehicle, ReadVehicleDto.class))
                        .build()
                )
                .email(user.getEmail())
                .id(user.getId())
                .build();
    }

    @Override
    public void updateLoggedInUser(UpdateUserDto updateUserDto) {
        User user = getLoggedInUser();
        updateUser(user, updateUserDto);
    }

    private void updateUser(User user, UpdateUserDto updateUserDto) {
        String password = updateUserDto.getPassword();
        String phoneNumber = updateUserDto.getPhoneNumber();
        Long roleId = updateUserDto.getRole();

        if (password != null && password.length() > 0) {
            user.setPassword(pe.encode(password));
        }
        if (phoneNumber != null && phoneNumber.length() > 0) {
            user.getCustomer().setPhoneNumber(phoneNumber);
        }
        if (roleId != null) {
            Role role = roleService.findById(roleId);

            verifyIfNeedToCreateEmployee(updateUserDto.getName(), user);
            user.setRole(role);
        }

        userRepository.save(user);
    }
}
