
package edu.ntnu.idatt2105.quizapp.services.quiz;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizAttemptDto;
import edu.ntnu.idatt2105.quizapp.model.user.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizAttemptRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizRepository;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the QuizAttemptService class. The tests are done with Mockito.
 * The tests are testing the functionality of the methods in the QuizAttemptService class.
 *
 * @version 1.0
 * @author Jeffrey Yaw Annor Tabiri
 */
@ExtendWith(MockitoExtension.class)
class QuizAttemptServiceTest {

  @InjectMocks
  QuizAttemptService quizAttemptService;

  @Mock
  QuizAttemptRepository quizAttemptRepository;

  @Mock
  QuizRepository quizRepository;

  @Mock
  UserRepository userRepository;

  Quiz quiz;

  QuizAttemptDto quizAttemptDto;

  User user;

  @BeforeEach
  void setUp() {
    quiz = QuizModelTestUtil.createQuizA();
    quizAttemptDto = TestUtil.createQuizAttemptDtoA();
    user = TestUtil.createUserA();
  }

  @Test
  void QuizAttemptServiceTest_SaveQuizAttempt_ReturnSavedQuizAttempt() {

    // Arrange
    when(quizRepository.findQuizById(quizAttemptDto.getQuizId())).thenReturn(Optional.ofNullable(quiz));
    when(userRepository.findUserByUsernameIgnoreCase(user.getUsername())).thenReturn(Optional.ofNullable(user));
    when(quizAttemptRepository.save(any(QuizAttempt.class))).thenReturn(new QuizAttempt());

    // Act
    quizAttemptService.saveQuizAttempt(quizAttemptDto, user.getUsername());

    // Assert
    assertDoesNotThrow(() -> quizAttemptService.saveQuizAttempt(quizAttemptDto, user.getUsername()));
  }

  @Test
  void QuizAttemptServiceTest_SaveQuizAttemptWithNoUser_ReturnException() {

    // Arrange
    when(quizRepository.findQuizById(quizAttemptDto.getQuizId())).thenReturn(Optional.ofNullable(quiz));
    when(userRepository.findUserByUsernameIgnoreCase(user.getUsername())).thenReturn(Optional.ofNullable(null));

    // Assert
    assertThrows(UsernameNotFoundException.class, () ->
            quizAttemptService.saveQuizAttempt(quizAttemptDto, user.getUsername()));

  }


}
