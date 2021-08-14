package com.backend.api.exceptions;

import lombok.Getter;

@Getter
public class ObjectNotFoundException extends RuntimeException {

  private String title;

  public ObjectNotFoundException(String msg, String title) {
    super(msg);
    this.title = title;
  }

  public ObjectNotFoundException(String msg, Throwable cause, String title) {
    super(msg, cause);
  }


}
