package edu.ntnu.idatt2105.quizapp.dto.quiz.creation;


import lombok.Builder;
import lombok.Data;

/**
 * DTO class used for a response when creating a quiz.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Data
@Builder
public class QuizCreationResponseDTO {
  private long quizId;
  private String errorMessage;
}
