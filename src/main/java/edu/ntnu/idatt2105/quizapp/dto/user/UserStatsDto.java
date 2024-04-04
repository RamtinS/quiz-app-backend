package edu.ntnu.idatt2105.quizapp.dto.user;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
public class UserStatsDto {
  private Map<LocalDate, Long> quizAttemptsLastSevenDays;
  private int totalQuizAttempts;
  private int totalScore;
}
