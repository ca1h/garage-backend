package com.backend.api.domain;

import com.backend.api.domain.enums.Permission;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class User extends Base {
  @NotNull
  private String email;

  @NotNull
  private String password;

  @OneToOne(mappedBy = "user")
  private Customer customer;

  @OneToOne(mappedBy = "user")
  private Employee employee;

  @ManyToOne
  private Role role;

  public Set<Permission> fetchAndFlattenPermissions() {
    var permissions = role.getPermissions();
    var hasAllPermission = permissions.stream()
        .anyMatch(permission -> permission == Permission.ALL);

    if (hasAllPermission) {
      return Set.of(Permission.values());
    }

    return permissions;
  }
}