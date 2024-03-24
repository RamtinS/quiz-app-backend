package edu.ntnu.idatt2105.quizapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * Represents a role entity in the application.
 * A "Role" refers to a specific type of authority or permission
 * granted to the user within the system.
 * Implements "GrantedAuthority" from Spring Security to handle authorities.
 *
 * @version 1.0
 * @since 2024-03-22
 * @author Jytabiri
 * @see GrantedAuthority
 */
@Entity
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(
          name = "role_id"
  )

  private Long roleId;

  @Column (
          name = "authority"
  )
  private String authority;

  @Override
  public String getAuthority() {
    return this.authority;
  }
}
