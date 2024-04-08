
package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.AnswerDto;
import edu.ntnu.idatt2105.quizapp.model.quiz.Answer;
import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerMapperTest {

  Answer answer;

  AnswerDto answerDTO;

  @BeforeEach
  void setUp() {
    answer = QuizModelTestUtil.createAnswerA();
    answerDTO = QuizModelTestUtil.createAnswerDTOA();
  }

  @Test
  void AnswerMapper_MapToAnswerDTO_ReturnAnswerDto() {
    //arrange
    AnswerMapper answerMapper = new AnswerMapper();
    //act
    AnswerDto expectedAnswerDTO = answerMapper.mapToAnswerDto(answer);
    //assert
    assertEquals(answer.getAnswerText(), expectedAnswerDTO.getAnswerText());
  }

  @Test
  void AnswerMapper_MapToAnswer_ReturnAnswer() {
    //arrange
    AnswerMapper answerMapper = new AnswerMapper();
    //act
    Answer expectedAnswer = answerMapper.mapToAnswer(answerDTO);
    //assert
    assertEquals(answerDTO.getAnswerText(), expectedAnswer.getAnswerText());
  }
}
