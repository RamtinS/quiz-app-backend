package edu.ntnu.idatt2105.quizapp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing new user details.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditUserDto {
  private String newPassword;
  private String newEmail;
  private String newName;
  private String newSurname;
}
