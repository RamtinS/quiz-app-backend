package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.AnswerDto;
import edu.ntnu.idatt2105.quizapp.model.quiz.Answer;
import org.springframework.stereotype.Component;

/**
 * Mapper class for mapping Answer objects to AnswerDTO objects and vice versa.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Component
public class AnswerMapper {

  /**
   * Maps an Answer object to an AnswerDTO object.
   *
   * @param answer The Answer object to map.
   * @return The mapped AnswerDTO object.
   */
  public AnswerDto mapToAnswerDto(Answer answer) {
    return AnswerDto.builder()
        .answerText(answer.getAnswerText())
        .isCorrect(answer.getIsCorrect())
        .build();
  }

  /**
   * Maps an AnswerDTO object to an Answer object.
   *
   * @param answerDto The AnswerDTO object to map.
   * @return The mapped Answer object.
   */
  public Answer mapToAnswer(AnswerDto answerDto) {
    return Answer.builder()
        .answerText(answerDto.getAnswerText())
        .isCorrect(answerDto.isCorrect())
        .build();
  }

}
