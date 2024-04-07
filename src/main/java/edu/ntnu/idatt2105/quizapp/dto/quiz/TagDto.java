package edu.ntnu.idatt2105.quizapp.dto.quiz;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class used for creating a tag to give extra clarity to quiz.
 *
 * @author Jeffrey Tabiri
 * @version 1.0
 * @since 2024-03-30
 */
@JsonTypeName("TagDTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
  private String description;
}
