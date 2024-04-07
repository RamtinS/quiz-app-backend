package edu.ntnu.idatt2105.quizapp.dto.quiz;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO class used for creating a multiple choice question.
 * Contains the question text and a list of answers.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Data()
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("MultipleChoiceQuestionDTO")
public class MultipleChoiceQuestionDto extends QuestionDto {
  private List<AnswerDto> answers;
}
