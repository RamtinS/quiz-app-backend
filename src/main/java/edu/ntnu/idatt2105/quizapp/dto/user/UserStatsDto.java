package edu.ntnu.idatt2105.quizapp.dto.user;

import java.time.LocalDate;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * DTO class used for user statistics.
 * Contains the number of quiz attempts the last seven days, total quiz attempts and total score.
 *
 * @version 1.0
 * @author  Ramtin Samavat
 */
@Data
@Builder
public class UserStatsDto {
  private Map<LocalDate, Long> quizAttemptsLastSevenDays;
  private int totalQuizAttempts;
  private int totalScore;
}
