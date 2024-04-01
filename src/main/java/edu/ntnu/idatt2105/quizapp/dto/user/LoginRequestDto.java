package edu.ntnu.idatt2105.quizapp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Data Transfer Object (DTO) containing user login information.
 *
 * @author Jeff Tabiri
 * @author Ramtin Samavat
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

  @NonNull
  private String username;

  @NonNull
  private String password;
}
