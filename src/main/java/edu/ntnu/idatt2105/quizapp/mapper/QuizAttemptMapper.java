package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizAttemptDto;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;

public class QuizAttemptMapper {

  /**
   * Maps a QuizAttempt object to an QuizAttemptDto object.
   *
   * @param quizAttempt is the quiz attempt we would want to convert
   * @return The mapped quizAttemptDto object.
   */
  public QuizAttemptDto mapToQuizAttemptDto(QuizAttempt quizAttempt) {
    return QuizAttemptDto.builder()
            .score(quizAttempt.getScore())
            .date(quizAttempt.getAttemptDate())
            .username(quizAttempt.getUser().getUsername())
            .build();
  }

  /**
   * Maps a QuizAttemptDto object to an QuizAttempt object.
   *
   * @param quizAttemptDto The quizAttemptDto object to map.
   * @return The mapped tag object.
   */
  public QuizAttempt mapToQuizAttempt(QuizAttemptDto quizAttemptDto, User user) {
    return QuizAttempt.builder()
            .score(quizAttemptDto.getScore())
            .attemptDate(quizAttemptDto.getDate())
            .user(user)
            .build();
  }
}
