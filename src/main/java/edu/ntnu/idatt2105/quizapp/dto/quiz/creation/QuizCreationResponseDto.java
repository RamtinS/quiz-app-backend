package edu.ntnu.idatt2105.quizapp.dto.quiz.creation;


import lombok.Builder;
import lombok.Data;

/**
 * DTO class used for a response when creating a quiz.
 * Contains the quiz id and an error message if the quiz was not created.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Data
@Builder
public class QuizCreationResponseDto {
  private long quizId;
}
