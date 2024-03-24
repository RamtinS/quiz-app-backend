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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


/**
 * Represents a user entity in the application. A user class represents a user entity in
 * an application and encapsulates essential information.
 * This class implements the Spring Security UserDetails interface.
 *
 * @author Jytabiri
 * @version 1.0
 * @since 2024-03-22
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
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(
          name = "user_id"
  )
  private Long userId;

  @Column(
          name = "username",
          nullable = false,
          unique = true
  )
  private String username;

  @Column(
          name = "hash",
          nullable = false
  )
  private String password;

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
