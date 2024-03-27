package edu.ntnu.idatt2105.quizapp.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO class used for public user information.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Data
@Builder
public class PublicUserInformationDTO {
  private String username;
}
