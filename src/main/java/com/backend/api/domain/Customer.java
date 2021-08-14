package com.backend.api.domain;

import com.backend.api.dtos.crud.update.UpdateCustomerDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Customer extends Base {

  @NotNull
  private String name;

  @OneToOne
  private User user;

  @OneToOne(mappedBy = "customer")
  private Vehicle vehicle;

  @NotNull
  private String phoneNumber;

  public void update(UpdateCustomerDto updateCustomerDto) {
    name = updateCustomerDto.getName();
    phoneNumber = updateCustomerDto.getPhoneNumber();
  }
}
