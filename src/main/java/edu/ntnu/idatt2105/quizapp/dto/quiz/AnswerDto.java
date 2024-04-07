package edu.ntnu.idatt2105.quizapp.dto.quiz;

import lombok.Builder;
import lombok.Data;

/**
 * DTO class used for creating an answer to a question.
 * Contains the answer text and if the answer is correct or not.
 *
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Data
@Builder
public class AnswerDto {
  private String answerText;
  private boolean isCorrect;
}
