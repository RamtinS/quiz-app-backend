package edu.ntnu.idatt2105.quizapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
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
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "first_name")
  private String name;

  @Column(name = "last_name")
  private String surName;

  /**
   * A many-to-many relational table which tracks the roles/authorities associated with the user.
   */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_role_junction",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {@JoinColumn(name = "role_id")}
  )
  private Set<Role> authorities;

  /**
   * Retrieves the roles/authorities associated with this user.
   *
   * @return The collection of granted permissions.
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
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
