package edu.ntnu.idatt2105.quizapp.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The user class represents a user entity in the application.
 * This class implements the Spring Security UserDetails interface.
 *
 * @author Jeffrey Tabiri
 * @author Ramtin Samavat
 * @version 1.0
 * @since 2024-03-25
 * @see UserDetails
 */
@Entity
@Table(name = "users")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  @Setter(AccessLevel.NONE)
  private Long userId;

  @Column(name = "username", nullable = false, unique = true)
  @NonNull
  private String username;

  @Column(name = "password", nullable = false)
  @NonNull
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "first_name")
  private String name;

  @Column(name = "last_name")
  private String surName;

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Role role;

  /**
   * Retrieves the roles/authorities associated with this user.
   *
   * @return The collection of granted permissions.
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public @NonNull String getPassword() {
    return this.password;
  }

  @Override
  public @NonNull String getUsername() {
    return this.username;
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
}
