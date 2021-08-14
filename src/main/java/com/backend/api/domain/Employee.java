package com.backend.api.domain;

import com.backend.api.dtos.crud.update.UpdateEmployeeDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Employee extends Base {

  @OneToOne
  @NotNull
  private User user;

  @NotNull
  private String name;

  @OneToMany(mappedBy = "employee")
  private List<Booking> bookings;

  public void update(UpdateEmployeeDto updateEmployeeDto) {
    name = updateEmployeeDto.getName();
  }
}
