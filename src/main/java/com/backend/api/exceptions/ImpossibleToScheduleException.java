package com.backend.api.exceptions;

import lombok.Getter;

@Getter
public class ImpossibleToScheduleException extends RuntimeException {

  private String title;

  public ImpossibleToScheduleException(String msg, String title) {
    super(msg);
    this.title = title;
  }

  public ImpossibleToScheduleException(String msg, Throwable cause, String title) {
    super(msg, cause);
  }


}
