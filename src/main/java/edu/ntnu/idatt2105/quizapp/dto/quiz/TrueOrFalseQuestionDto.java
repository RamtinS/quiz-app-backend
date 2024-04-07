package edu.ntnu.idatt2105.quizapp.dto.quiz;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * DTO class used for creating a true or false question.
 * Contains the question text and if the question is correct or not.
 *
 * @version 1.0
 * @author  Tobias Oftedal
 */
@Data()
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

@JsonTypeName("TrueOrFalseQuestionDTO")
public class TrueOrFalseQuestionDto extends QuestionDto {
  private Boolean questionIsCorrect;
}