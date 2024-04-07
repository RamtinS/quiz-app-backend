package edu.ntnu.idatt2105.quizapp.dto.quiz;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO class used for creating a quiz question.
 * Contains the question text and the type of question.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "questionType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = TrueOrFalseQuestionDto.class, name = "TrueOrFalseQuestionDTO"),
    @JsonSubTypes.Type(value = MultipleChoiceQuestionDto.class, name = "MultipleChoiceQuestionDTO")}
)
public abstract class QuestionDto {
  private String questionText;
}
