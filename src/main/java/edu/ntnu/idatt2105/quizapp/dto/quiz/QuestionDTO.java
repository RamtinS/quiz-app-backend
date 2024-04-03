package edu.ntnu.idatt2105.quizapp.dto.quiz;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO class used for creating a quiz question.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "questionType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = TrueOrFalseQuestionDTO.class, name = "TrueOrFalseQuestionDTO"),
    @JsonSubTypes.Type(value = MultipleChoiceQuestionDTO.class, name = "MultipleChoiceQuestionDTO")}
)
public abstract class QuestionDTO {
  private String questionText;
}
