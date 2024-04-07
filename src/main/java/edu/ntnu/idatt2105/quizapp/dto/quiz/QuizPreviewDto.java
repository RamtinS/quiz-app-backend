package edu.ntnu.idatt2105.quizapp.dto.quiz;

import lombok.Builder;
import lombok.Data;

/**
 * Dto for previewing a quiz. Demands less transferring bandwidth than the full quiz dto.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Builder
@Data
public class QuizPreviewDto {
  private long id;
  private String title;
  private String description;
  private boolean open;
}
