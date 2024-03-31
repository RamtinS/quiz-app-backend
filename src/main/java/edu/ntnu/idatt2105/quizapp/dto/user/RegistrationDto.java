package edu.ntnu.idatt2105.quizapp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * A data transfer object representing user registration information.
 *
 * @version 1.0
 * @since 2024-03-22
 * @author Jytabiri
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

  @NonNull
  private String username;

  @NonNull
  private String password;

  private String email;

  private String name;

  private String surname;
  
}
