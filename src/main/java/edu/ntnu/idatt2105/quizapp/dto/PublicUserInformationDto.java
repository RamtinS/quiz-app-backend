package edu.ntnu.idatt2105.quizapp.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO class used for public user information.
 * Contains the username of the user.
 * This class is used to send information about a user to the client.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Data
@Builder
public class PublicUserInformationDto {
  private String username;
}
