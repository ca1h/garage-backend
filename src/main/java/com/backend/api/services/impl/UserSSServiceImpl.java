package com.backend.api.services.impl;

import com.backend.api.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserSSServiceImpl {

  public static UserSS authenticated() {
    try {
      return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } catch (Exception e) {
      return null;
    }
  }
}
