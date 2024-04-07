package edu.ntnu.idatt2105.quizapp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * DTO class used for authentication.
 * Contains the token.
 *
 * @version 1.0
 * @author  Jeffrey Tabiri
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {
  @NonNull
  private String token;

}
