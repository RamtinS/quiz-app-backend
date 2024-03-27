package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizDTO;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizPreviewDTO;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationRequestDTO;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper class for mapping Quiz objects to QuizDTO objects and vice versa.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Component
@RequiredArgsConstructor
public class QuizMapper {
  @NonNull
  UserMapper userMapper;
  @NonNull
  QuestionMapper questionMapper;

  /**
   * Maps a Quiz object to a QuizDTO object.
   *
   * @param quiz The Quiz object to map.
   * @return The mapped QuizDTO object.
   */
  public QuizDTO mapToQuizDTO(Quiz quiz) {
    return QuizDTO.builder()
        .name(quiz.getName())
        .description(quiz.getDescription())
        .author(userMapper.mapToPublicUserInformation(quiz.getAuthor()))
        .questions(quiz.getQuestions().stream()
            .map(questionMapper::mapToQuizQuestionDTO)
            .toList())
        .build();
  }

  /**
   * Maps a Quiz object to a QuizPreviewDTO object.
   *
   * @param quiz The quiz object to map.
   * @return The mapped Quiz object.
   */
  public QuizPreviewDTO mapToQuizPreviewDTO(Quiz quiz) {
    return QuizPreviewDTO.builder()
        .id(quiz.getId())
        .title(quiz.getName())
        .description(quiz.getDescription())
        .open(quiz.isOpen())
        .build();
  }

  /**
   * Maps a QuizCreationRequestDTO object to a Quiz object.
   *
   * @param quizCreationRequestDTO The QuizCreationRequestDTO object to map.
   * @param user                   The user that created the quiz.
   * @return The mapped Quiz object.
   */
  public Quiz mapToQuiz(QuizCreationRequestDTO quizCreationRequestDTO, User user) {
    Quiz createdQuiz = Quiz.builder()
        .name(quizCreationRequestDTO.getTitle())
        .description(quizCreationRequestDTO.getDescription())
        .questions(quizCreationRequestDTO.getQuestions().stream()
            .map(questionMapper::mapToQuizQuestion)
            .toList())
        .author(user)
        .build();

    createdQuiz.getQuestions().forEach(question -> {
      question.setQuiz(createdQuiz);
      question.getAnswers().forEach(answer -> answer.setQuizQuestion(question));
    });
    return createdQuiz;
  }

}




