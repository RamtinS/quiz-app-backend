package edu.ntnu.idatt2105.quizapp.dto.quiz.creation;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizQuestionDTO;
import edu.ntnu.idatt2105.quizapp.dto.quiz.TagDto;
import edu.ntnu.idatt2105.quizapp.model.quiz.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * DTO class used for requesting to create a quiz.
 * Contains the title, description, questions and open status of the quiz.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Builder
@Data
public class QuizCreationRequestDTO {
  @NotNull
  @NotBlank
  private String title;
  private String description;
  @NotNull
  private List<QuizQuestionDTO> questions;
  private List<TagDto> tags;
  private boolean open;
}

