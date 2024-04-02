package edu.ntnu.idatt2105.quizapp.dto.quiz;

import edu.ntnu.idatt2105.quizapp.dto.PublicUserInformationDTO;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * Dto to represent a quiz.
 *
 * @author Tobias Oftedal
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 * @since 2024-03-27
 */
@Data
@Builder
public class QuizDto {
  private String name;
  private String description;
  private String categoryDescription;
  private List<QuestionDTO> questions;
  private List<TagDto> tags;
  private PublicUserInformationDTO author;
  private boolean isOpen;
}
