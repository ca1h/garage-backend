package com.backend.api.services.impl;

import com.backend.api.domain.User;
import com.backend.api.repositories.UserRepository;
import com.backend.api.security.UserSS;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class
UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository repo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = repo.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException(email);
    }
    return new UserSS(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
  }
}
