package edu.ntnu.idatt2105.quizapp.dto.quiz;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data()
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MultipleChoiceQuestionDTO extends QuestionDTO {
  private List<AnswerDTO> answers;
}
