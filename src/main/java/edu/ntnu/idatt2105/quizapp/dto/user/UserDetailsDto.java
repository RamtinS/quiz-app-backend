package edu.ntnu.idatt2105.quizapp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing user details.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
  private String email;
  private String name;
  private String surname;
}
