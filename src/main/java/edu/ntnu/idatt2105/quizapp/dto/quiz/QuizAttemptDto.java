package edu.ntnu.idatt2105.quizapp.dto.quiz;

import lombok.Data;

/**
 * DTO for representing information related to a quiz attempt.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
@Data
public class QuizAttemptDto {
  private long quizId;
  private int score;
}
