package edu.ntnu.idatt2105.quizapp.services.quiz;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizAttemptDto;
import edu.ntnu.idatt2105.quizapp.exception.quiz.QuizNotFoundException;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizAttemptRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizRepository;
import java.time.LocalDate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class that encapsulates the logic for handling quiz attempt-related operations.
 * It uses the {@link QuizAttemptRepository} to perform the operations in the database.
 *
 * @author Ramtin Samavat
 * @version  1.0
 */
@Service
@RequiredArgsConstructor
public class QuizAttemptService {

  private final QuizAttemptRepository quizAttemptRepository;
  private final QuizRepository quizRepository;
  private final UserRepository userRepository;

  /**
   * The method saves a quiz attempt to the database.
   *
   * @param quizAttemptDto DTO containing quiz attempt information.
   * @throws UsernameNotFoundException If the user is not found in the database.
   */
  public void saveQuizAttempt(@NonNull QuizAttemptDto quizAttemptDto, @NonNull String username)
          throws UsernameNotFoundException {

    Quiz quiz = quizRepository.findQuizById(quizAttemptDto.getQuizId())
            .orElseThrow(QuizNotFoundException::new);

    User user = userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() ->
            new UsernameNotFoundException("User with username " + username + " not found."));

    QuizAttempt quizAttempt = QuizAttempt.builder()
            .quiz(quiz)
            .user(user)
            .score(quizAttemptDto.getScore())
            .timestamp(LocalDate.now())
            .build();

    quizAttemptRepository.save(quizAttempt);
  }
}
