package edu.ntnu.idatt2105.quizapp.util;

import edu.ntnu.idatt2105.quizapp.dto.user.LoginRequestDto;
import edu.ntnu.idatt2105.quizapp.dto.user.RegistrationDto;
import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;
import edu.ntnu.idatt2105.quizapp.model.quiz.Tag;
import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;

/**
 * Utility class for mock creation of models.
 *
 * @version 1.0
 * @since 2024-03-25
 * @author Jytabiri
 */
public class TestUtil {

  public static User createUserA() {
    return User.builder()
            .username("Mark")
            .password("PasswordPassword")
            .email("test@mail.com")
            .name("testName")
            .role(Role.USER)
            .surName("Ply")
            .build();
  }

  public static User createUserB() {
    return User.builder()
            .username("Alice")
            .password("Alice123")
            .email("alice@example.com")
            .name("Alice")
            .role(Role.ADMIN)
            .surName("Johnson")
            .build();
  }

  public static User createUserC() {
    return User.builder()
            .username("Zac")
            .password("Password")
            .build();
  }

  public static User createUserD() {
    return User.builder()
            .username("Geoffrey")
            .password("Password")
            .build();
  }

  public static Tag createTagA() {
    return Tag.builder()
            .description("Integrals")
            .build();
  }

  public static QuizAttempt createQuizAttemptA() {

    Quiz quiz = QuizModelTestUtil.createQuizA();
    User user = TestUtil.createUserA();

    return QuizAttempt.builder()
            .quiz(quiz)
            .user(user)
            .build();
  }

  public static LoginRequestDto createLoginDtoA() {
    return new LoginRequestDto("Mark", "PasswordPassword");
  }

  public static RegistrationDto createRegistrationDtoA()  {
    return new RegistrationDto("Mark",
            "Draven",
            "email@example.com",
            "Mark",
            "Draven");
  }


}
