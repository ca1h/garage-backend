package com.backend.api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum BookingType {
  ANNUAL_SERVICE("Annual Service", 100F),
  MAJOR_SERVICE("Major service", 200F),
  REPAIR_FAULT("Repair/Fault", 150F),
  MAJOR_REPAIR("Major repair", 200F);

  private String name;
  private Float minPrice;
}
