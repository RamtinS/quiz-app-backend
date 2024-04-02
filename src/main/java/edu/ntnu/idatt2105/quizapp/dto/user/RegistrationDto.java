package edu.ntnu.idatt2105.quizapp.dto.user;

import jakarta.validation.constraints.Size;
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
public class RegistrationDto {

  @NonNull
  private String c;

  @NonNull
  private String password;

  @NonNull
  private String email;

  @NonNull
  private String name;

  @NonNull
  private String surname;
}
