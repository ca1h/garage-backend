package com.backend.api.controllers.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ValidationError extends StandardError {

  private final List<FieldMessage> errors = new ArrayList<>();

  public ValidationError(Integer status) {
    super(status, "Validation error", "The following fields are incorrect:");
  }

  public void addError(String fieldName, String message) {
    errors.add(new FieldMessage(fieldName, message));
  }

}
