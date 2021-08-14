package com.backend.api.domain;

import com.backend.api.domain.enums.BookingStatus;
import com.backend.api.domain.enums.BookingType;
import com.backend.api.dtos.crud.update.UpdateBookingDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Booking extends Base {

  @ManyToOne
  private Employee employee;

  @ManyToOne
  @NotNull
  private Customer customer;

  @NotNull
  private LocalDate date;

  @NotNull
  @Column(name = "booking_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private BookingStatus bookingStatus;

  @NotNull
  @Column(name = "booking_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private BookingType bookingType;

  @ManyToMany
  @JoinTable(name = "supply_booking", joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "supply_id"))
  private List<Supply> supplies = new ArrayList<>();

  @CollectionTable(
      joinColumns = {
          @JoinColumn(name = "booking_id", referencedColumnName = "id", nullable = false)
      },
      name = "booking_comment"
  )
  @Column(name = "comment")
  @ElementCollection
  private List<String> comments = new ArrayList<>();

  private String description;
  private Float total;

  public void update(UpdateBookingDto updateBookingDto) {
  }
}
