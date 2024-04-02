package edu.ntnu.idatt2105.quizapp.dto.quiz;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * DTO Class for quiz attempts.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 * @since 2024-04-02
 */
@Data
@Builder
public class QuizAttemptDto {
  private int score;
  private Date date;
  private String username;
}
