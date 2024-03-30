package edu.ntnu.idatt2105.quizapp.dto.quiz;

import lombok.Builder;
import lombok.Data;

/**
 * DTO class used for creating a tag to give extra clarity to quiz.
 *
 * @author Jeffrey Tabiri
 * @version 1.0
 * @since 2024-03-30
 */
@Data
@Builder
public class TagDto {
  private String description;
}
