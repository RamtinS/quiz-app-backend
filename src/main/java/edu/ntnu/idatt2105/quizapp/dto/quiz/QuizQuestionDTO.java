package edu.ntnu.idatt2105.quizapp.dto.quiz;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * DTO class used for creating a quiz question.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Data
@Builder
public class QuizQuestionDTO {
    private String questionText;
    private List<AnswerDTO> answers;
}
