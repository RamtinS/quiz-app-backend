package edu.ntnu.idatt2105.quizapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * Data transfer object (DTO) representing user's feedback information.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class FeedbackDto {

  @NonNull
  private final String email;

  private final String name;

  private final String surname;

  @NonNull
  private final String content;
}
