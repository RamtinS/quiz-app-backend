package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizPreviewDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationRequestDto;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper class for mapping Quiz objects to QuizDTO objects and vice versa.
 *
 * @author Tobias Oftedal
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class QuizMapper {

  @NonNull
  UserMapper userMapper;

  @NonNull
  QuestionMapper questionMapper;

  @NonNull
  TagMapper tagMapper;

  /**
   * Maps a Quiz object to a QuizDTO object.
   *
   * @param quiz The Quiz object to map.
   * @return The mapped QuizDTO object.
   */
  public QuizDto mapToQuizDto(Quiz quiz) {
    return QuizDto.builder()
        .quizId(quiz.getId())
        .name(quiz.getName())
        .description(quiz.getDescription())
        .author(userMapper.mapToPublicUserInformation(quiz.getAuthor()))
        .tags(quiz.getTags().stream()
            .map(tagMapper::mapToTagDto).toList())

        .questions(quiz.getQuestions().stream()
            .map(questionMapper::mapToQuizQuestionDto)
            .toList())

        .isOpen(quiz.getIsOpen())
        .build();
  }

  /**
   * Maps a Quiz object to a QuizPreviewDTO object.
   *
   * @param quiz The quiz object to map.
   * @return The mapped Quiz object.
   */
  public QuizPreviewDto mapToQuizPreviewDto(Quiz quiz) {
    return QuizPreviewDto.builder()
        .id(quiz.getId())
        .title(quiz.getName())
        .description(quiz.getDescription())
        .open(quiz.getIsOpen())
        .build();
  }

  /**
   * Maps a QuizCreationRequestDTO object to a Quiz object.
   *
   * @param quizCreationRequestDto The QuizCreationRequestDTO object to map.
   * @param user                   The user that created the quiz.
   * @return The mapped Quiz object.
   */
  public Quiz mapToQuiz(QuizCreationRequestDto quizCreationRequestDto, User user) {

    Quiz createdQuiz = Quiz.builder()
        .name(quizCreationRequestDto.getTitle())
        .description(quizCreationRequestDto.getDescription())
        .tags(quizCreationRequestDto.getTags().stream()
            .map(tagMapper::mapToTag).toList())
        .questions(
            quizCreationRequestDto.getQuestions()
                .stream()
                .map(a -> questionMapper.mapToQuestion(a))
                .toList())

        .author(user)
        .isOpen(quizCreationRequestDto.isOpen())
        .build();

    createdQuiz.getQuestions().forEach(question -> question.setQuiz(createdQuiz));

    return createdQuiz;
  }
}
