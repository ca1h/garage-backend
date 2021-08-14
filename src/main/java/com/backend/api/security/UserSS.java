package com.backend.api.security;

import com.backend.api.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserSS implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public UserSS() {
  }

  public UserSS(Long id, String email, String password, Role role) {
    super();
    this.id = id;
    this.email = email;
    this.password = password;
    this.authorities = List.of(new SimpleGrantedAuthority(role.getName()));
  }

  public Long getId() {
    return id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return "UserSS [email=" + email + ", id=" + id + ", password=" + password + "]";
  }
}
