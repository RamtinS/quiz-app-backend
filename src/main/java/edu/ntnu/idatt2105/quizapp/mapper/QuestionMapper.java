package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizQuestionDTO;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizQuestion;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper class for mapping QuizQuestion objects to QuizQuestionDTO objects and vice versa.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Component
@RequiredArgsConstructor
public class QuestionMapper {
  @NonNull
  AnswerMapper answerMapper;

  /**
   * Maps a QuizQuestion object to a QuizQuestionDTO object.
   *
   * @param question The QuizQuestion object to map.
   * @return The mapped QuizQuestionDTO object.
   */
  public QuizQuestionDTO mapToQuizQuestionDTO(QuizQuestion question) {
    return QuizQuestionDTO.builder()
        .questionText(question.getQuestionText())
        .answers(question.getAnswers()
            .stream()
            .map(answerMapper::mapToAnswerDTO)
            .toList())
        .build();
  }

  /**
   * Maps a QuizQuestionDTO object to a QuizQuestion object.
   *
   * @param questionDTO The QuizQuestionDTO object to map.
   * @return The mapped QuizQuestion object.
   */
  public QuizQuestion mapToQuizQuestion(QuizQuestionDTO questionDTO) {
    return QuizQuestion.builder()
        .questionText(questionDTO.getQuestionText())
        .answers(questionDTO.getAnswers()
            .stream()
            .map(answerMapper::mapToAnswer)
            .toList())
        .build();
  }
}
