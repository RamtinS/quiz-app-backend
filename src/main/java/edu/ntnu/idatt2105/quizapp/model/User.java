package edu.ntnu.idatt2105.quizapp.model;

import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
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
 * @see UserDetails
 */
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  @Setter(AccessLevel.NONE)
  private Long userId;

  @Column(name = "username", nullable = false, unique = true)
  @NonNull
  @Setter(AccessLevel.NONE)
  private String username;

  @Column(name = "password", nullable = false)
  @NonNull
  private String password;

  @Column(name = "email", nullable = false, unique = true)
  @NonNull
  private String email;

  @Column(name = "first_name", nullable = false)
  @NonNull
  private String name;

  @Column(name = "last_name", nullable = false)
  @NonNull
  private String surName;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  @NonNull
  private Role role;

  @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
  private List<Quiz> quizzes;

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

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    User user = (User) object;
    return Objects.equals(userId, user.userId) &&
        Objects.equals(username, user.username) &&
        Objects.equals(password, user.password) &&
        Objects.equals(email, user.email) && Objects.equals(name, user.name) &&
        Objects.equals(surName, user.surName) && role == user.role &&
        Objects.equals(quizzes, user.quizzes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, username, password, email, name, surName, role);
  }
}
