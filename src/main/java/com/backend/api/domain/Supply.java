package com.backend.api.domain;

import com.backend.api.dtos.crud.update.UpdateSupplyDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Supply extends Base {

  @ManyToMany(mappedBy = "supplies")
  List<Booking> bookings;
  @NotNull
  private Float price;
  @NotNull
  private String name;

  public void update(UpdateSupplyDto dto) {
    price = dto.getPrice();
    name = dto.getName();

  }

}
