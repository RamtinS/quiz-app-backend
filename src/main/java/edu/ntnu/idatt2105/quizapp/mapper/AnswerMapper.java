package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.AnswerDTO;
import edu.ntnu.idatt2105.quizapp.model.quiz.Answer;
import org.springframework.stereotype.Component;

/**
 * Mapper class for mapping Answer objects to AnswerDTO objects and vice versa.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Component
public class AnswerMapper {

  /**
   * Maps an Answer object to an AnswerDTO object.
   *
   * @param answer The Answer object to map.
   * @return The mapped AnswerDTO object.
   */
  public AnswerDTO mapToAnswerDTO(Answer answer) {
    return AnswerDTO.builder()
        .answerText(answer.getAnswerText())
        .isCorrect(answer.isCorrect())
        .build();
  }

  /**
   * Maps an AnswerDTO object to an Answer object.
   *
   * @param answerDTO The AnswerDTO object to map.
   * @return The mapped Answer object.
   */
  public Answer mapToAnswer(AnswerDTO answerDTO) {
    return Answer.builder()
        .answerText(answerDTO.getAnswerText())
        .isCorrect(answerDTO.isCorrect())
        .build();
  }

}
