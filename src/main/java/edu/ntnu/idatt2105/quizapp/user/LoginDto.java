package edu.ntnu.idatt2105.quizapp.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * A data transfer object containing user login information.
 *
 * @version 1.0
 * @since 2024-03-22
 * @author Jytabiri
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

  @NonNull
  private String username;

  @NonNull
  private String password;
}
