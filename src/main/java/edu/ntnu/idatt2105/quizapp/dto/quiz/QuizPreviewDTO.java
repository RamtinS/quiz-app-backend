package edu.ntnu.idatt2105.quizapp.dto.quiz;

import lombok.Builder;
import lombok.Data;

/**
 * Dto for previewing a quiz. Demands less transferring bandwidth than the full quiz dto.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Builder
@Data
public class QuizPreviewDTO {
  private long id;
  private String title;
  private String description;
  private boolean open;
}
