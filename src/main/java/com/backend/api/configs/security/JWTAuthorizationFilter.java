package com.backend.api.configs.security;

import com.backend.api.domain.User;
import com.backend.api.domain.enums.Permission;
import com.backend.api.repositories.UserRepository;
import com.backend.api.security.JWTUtil;
import com.backend.api.security.UserSS;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  private final JWTUtil jwtUtil;

  private final UserRepository userRepository;

  private final UserDetailsService userDetailsService;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
                                UserDetailsService userDetailsService, UserRepository userRepository) {
    super(authenticationManager);
    this.jwtUtil = jwtUtil;
    this.userDetailsService = userDetailsService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      String rawToken = header.substring(7);

      UsernamePasswordAuthenticationToken auth = getAuthentication(rawToken);
      if (auth != null) {
        UserSS user = (UserSS) auth.getPrincipal();
        Long id = user.getId();

        if (id == null) {
          chain.doFilter(request, response);
          return;
        }

        String uri = request.getRequestURI();
        String method = request.getMethod();

        User u = userRepository.findById(id).get();

        if (!isAllowedRoute(uri, method, u)) {
          chain.doFilter(request, response);
          return;
        }

        SecurityContextHolder.getContext().setAuthentication(auth);

      }
    }
    chain.doFilter(request, response);
  }

  private boolean isAllowedRoute(String uri, String method, User user) {
    Permission p = Permission.find(method, uri);
    return user.fetchAndFlattenPermissions().stream().anyMatch(permission -> permission.equals(p));
  }

  private UsernamePasswordAuthenticationToken getAuthentication(String token) {
    if (jwtUtil.isValidToken(token)) {
      String email = jwtUtil.getUsername(token);
      UserDetails user = userDetailsService.loadUserByUsername(email);
      return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
    return null;
  }
}
