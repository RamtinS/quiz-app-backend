package edu.ntnu.idatt2105.quizapp.dto.quiz;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type"
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
  private String description;
}
