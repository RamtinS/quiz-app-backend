package edu.ntnu.idatt2105.quizapp.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public abstract class QuestionDTO {
    private String questionText;
}
