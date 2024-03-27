package edu.ntnu.idatt2105.quizapp.dto.quiz;

import lombok.Builder;
import lombok.Data;

/**
 * DTO class used for creating an answer to a question.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Data
@Builder
public class AnswerDTO {
  private String answerText;
  private boolean isCorrect;
}
