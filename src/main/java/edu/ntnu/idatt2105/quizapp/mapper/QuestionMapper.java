package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.MultipleChoiceQuestionDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuestionDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.TrueOrFalseQuestionDto;
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
  public QuestionDto mapToQuizQuestionDto(Question question) {
    if (question instanceof MultipleChoiceQuestion) {
      return mapToQuizQuestionDtoFromMultipleChoice((MultipleChoiceQuestion) question);

    } else if (question instanceof TrueOrFalseQuestion) {
      return mapToQuizQuestionDtoFromTrueOrFalse((TrueOrFalseQuestion) question);
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
  public QuestionDto mapToQuizQuestionDtoFromTrueOrFalse(TrueOrFalseQuestion question) {
    return TrueOrFalseQuestionDto.builder()
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
  public QuestionDto mapToQuizQuestionDtoFromMultipleChoice(MultipleChoiceQuestion question) {

    return MultipleChoiceQuestionDto.builder()
        .answers(question.getAnswers()
            .stream()
            .map(answerMapper::mapToAnswerDto)
            .toList())
        .questionText(question.getQuestionText())
        .build();
  }

  /**
   * Maps a QuizQuestionDTO object to a QuizQuestion object.
   *
   * @param questionDto The QuizQuestionDTO object to map.
   * @return The mapped QuizQuestion object.
   */
  public Question mapToQuestion(QuestionDto questionDto) {

    if (questionDto instanceof MultipleChoiceQuestionDto) {
      return mapMultipleChoiceQuestionDtoToQuestion((MultipleChoiceQuestionDto) questionDto);
    } else if (questionDto instanceof TrueOrFalseQuestionDto) {
      return mapTrueOrFalsoToQuestion((TrueOrFalseQuestionDto) questionDto);
    } else {
      throw new IllegalArgumentException("Question type not supported");
    }
  }

  /**
   * Maps a MultipleChoiceQuestionDTO object to a MultipleChoiceQuestion object.
   *
   * @param questionDto The MultipleChoiceQuestionDTO object to map.
   * @return The mapped MultipleChoiceQuestion object.
   */
  public Question mapMultipleChoiceQuestionDtoToQuestion(MultipleChoiceQuestionDto questionDto) {
    MultipleChoiceQuestion question = MultipleChoiceQuestion.builder()
        .answers(questionDto.getAnswers()
            .stream()
            .map(answerMapper::mapToAnswer)
            .toList())
        .questionText(questionDto.getQuestionText())
        .build();
    question.getAnswers().forEach(answer -> answer.setQuestion(question));
    return question;
  }

  /**
   * Maps a TrueOrFalseQuestionDTO object to a TrueOrFalseQuestion object.
   *
   * @param questionDto The TrueOrFalseQuestionDTO object to map.
   * @return The mapped TrueOrFalseQuestion object.
   */
  public Question mapTrueOrFalsoToQuestion(TrueOrFalseQuestionDto questionDto) {
    return TrueOrFalseQuestion.builder()
        .questionIsCorrect(questionDto.getQuestionIsCorrect())
        .questionText(questionDto.getQuestionText())
        .build();
  }
}
