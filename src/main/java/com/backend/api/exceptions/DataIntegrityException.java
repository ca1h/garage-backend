package com.backend.api.exceptions;

import lombok.Getter;

@Getter
public class DataIntegrityException extends RuntimeException {

  private String title;

  public DataIntegrityException(String msg, String title) {
    super(msg);
    this.title = title;
  }

  public DataIntegrityException(String msg, Throwable cause) {
    super(msg, cause);
  }


}
