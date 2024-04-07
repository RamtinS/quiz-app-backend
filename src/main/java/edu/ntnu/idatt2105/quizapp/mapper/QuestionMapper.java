package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.MultipleChoiceQuestionDTO;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuestionDTO;
import edu.ntnu.idatt2105.quizapp.dto.quiz.TrueOrFalseQuestionDTO;
import edu.ntnu.idatt2105.quizapp.model.quiz.MultipleChoiceQuestion;
import edu.ntnu.idatt2105.quizapp.model.quiz.Question;
import edu.ntnu.idatt2105.quizapp.model.quiz.TrueOrFalseQuestion;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper class for mapping QuizQuestion objects to QuizQuestionDTO objects and vice versa.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class QuestionMapper {

  @NonNull
  private final AnswerMapper answerMapper;

  /**
   * Maps a QuizQuestion object to a QuizQuestionDTO object.
   *
   * @param question The QuizQuestion object to map.
   * @return The mapped QuizQuestionDTO object.
   */
  public QuestionDTO mapToQuizQuestionDTO(Question question) {
    if (question instanceof MultipleChoiceQuestion) {
      return mapToQuizQuestionDTOFromMultipleChoice((MultipleChoiceQuestion) question);

    } else if (question instanceof TrueOrFalseQuestion) {
      return mapToQuizQuestionDTOFromTrueOrFalse((TrueOrFalseQuestion) question);
    } else {
      throw new IllegalArgumentException("Question type not supported");

    }
  }

  /**
   * Maps a TrueOrFalseQuestion object to a TrueOrFalseQuestionDTO object.
   *
   * @param question The TrueOrFalseQuestion object to map.
   * @return The mapped TrueOrFalseQuestionDTO object.
   */
  public QuestionDTO mapToQuizQuestionDTOFromTrueOrFalse(TrueOrFalseQuestion question) {
    return TrueOrFalseQuestionDTO.builder()
        .questionIsCorrect(question.getQuestionIsCorrect())
        .questionText(question.getQuestionText())
        .build();
  }

  /**
   * Maps a MultipleChoiceQuestion object to a MultipleChoiceQuestionDTO object.
   *
   * @param question The MultipleChoiceQuestion object to map.
   * @return The mapped MultipleChoiceQuestionDTO object.
   */
  public QuestionDTO mapToQuizQuestionDTOFromMultipleChoice(MultipleChoiceQuestion question) {

    return MultipleChoiceQuestionDTO.builder()
        .answers(question.getAnswers()
            .stream()
            .map(answerMapper::mapToAnswerDTO)
            .toList())
        .questionText(question.getQuestionText())
        .build();
  }

  /**
   * Maps a QuizQuestionDTO object to a QuizQuestion object.
   *
   * @param questionDTO The QuizQuestionDTO object to map.
   * @return The mapped QuizQuestion object.
   */
  public Question mapToQuestion(QuestionDTO questionDTO) {

    if (questionDTO instanceof MultipleChoiceQuestionDTO) {
      return mapMultipleChoiceQuestionDTOToQuestion((MultipleChoiceQuestionDTO) questionDTO);
    } else if (questionDTO instanceof TrueOrFalseQuestionDTO) {
      return mapTrueOrFalsoToQuestion((TrueOrFalseQuestionDTO) questionDTO);
    } else {
      throw new IllegalArgumentException("Question type not supported");
    }
  }

  /**
   * Maps a MultipleChoiceQuestionDTO object to a MultipleChoiceQuestion object.
   *
   * @param questionDTO The MultipleChoiceQuestionDTO object to map.
   * @return The mapped MultipleChoiceQuestion object.
   */
  public Question mapMultipleChoiceQuestionDTOToQuestion(MultipleChoiceQuestionDTO questionDTO) {
    MultipleChoiceQuestion question = MultipleChoiceQuestion.builder()
        .answers(questionDTO.getAnswers()
            .stream()
            .map(answerMapper::mapToAnswer)
            .toList())
        .questionText(questionDTO.getQuestionText())
        .build();
    question.getAnswers().forEach(answer -> answer.setQuestion(question));
    return question;
  }

  /**
   * Maps a TrueOrFalseQuestionDTO object to a TrueOrFalseQuestion object.
   *
   * @param questionDTO The TrueOrFalseQuestionDTO object to map.
   * @return The mapped TrueOrFalseQuestion object.
   */
  public Question mapTrueOrFalsoToQuestion(TrueOrFalseQuestionDTO questionDTO) {
    return TrueOrFalseQuestion.builder()
        .questionIsCorrect(questionDTO.getQuestionIsCorrect())
        .questionText(questionDTO.getQuestionText())
        .build();
  }
}
