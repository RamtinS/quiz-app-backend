package edu.ntnu.idatt2105.quizapp.util;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizAttemptDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationRequestDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationResponseDto;
import edu.ntnu.idatt2105.quizapp.dto.user.EditUserDto;
import edu.ntnu.idatt2105.quizapp.dto.user.LoginRequestDto;
import edu.ntnu.idatt2105.quizapp.dto.user.RegistrationDto;
import edu.ntnu.idatt2105.quizapp.model.FeedbackMessage;
import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Category;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;
import edu.ntnu.idatt2105.quizapp.model.quiz.Tag;
import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;

import java.time.LocalDate;
import java.util.List;

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
            .username("Bob")
            .password("Bob456")
            .email("bob@example.com")
            .name("Bob")
            .role(Role.USER)
            .surName("Smith")
            .build();
  }

  public static User createUserD() {
    return User.builder()
            .username("Charlie")
            .password("Charlie789")
            .email("charlie@example.com")
            .name("Charlie")
            .role(Role.USER)
            .surName("Brown")
            .build();
  }

  public static Tag createTagA() {
    return Tag.builder()
            .description("Integrals")
            .build();
  }

  public static Category createCategoryA() {
    return Category.builder()
            .description("Physics")
            .quizzes(List.of(QuizModelTestUtil.createQuizA()))
            .build();
  }

  public static Category createCategoryB() {
    return Category.builder()
            .description("Maths")
            .quizzes(List.of(QuizModelTestUtil.createQuizA()))
            .build();
  }


  public static QuizAttempt createQuizAttemptA() {

    Quiz quiz = QuizModelTestUtil.createQuizA();
    User user = TestUtil.createUserA();

    return QuizAttempt.builder()
            .quiz(quiz)
            .user(user)
            .timestamp(LocalDate.ofYearDay(2020, 8))
            .score(37)
            .build();
  }

  public static LoginRequestDto createLoginDtoA() {
    return new LoginRequestDto("Mark", "PasswordPassword");
  }

  public static QuizCreationResponseDto createQuizCreationResponseDtoA() {
    return QuizCreationResponseDto.builder()
            .quizId(1L)
            .errorMessage("Error")
            .build();

  }

  public static QuizCreationRequestDto createQuizCreationRequestDtoA() {
    return QuizCreationRequestDto.builder()
            .title("Maths")
            .description("Maths")
            .open(true)
            .categoryDescription("Maths")
            .build();
  }


  public static RegistrationDto createRegistrationDtoA()  {
    return new RegistrationDto("Mark",
            "password",
            "email@example.com",
            "Mark",
            "Draven");
  }


  public static FeedbackMessage createFeedbackA() {
    return FeedbackMessage.builder()
            .timestamp(LocalDate.ofYearDay(2020, 8))
            .content("One")
            .surname("Two")
            .content("three")
            .email("Four")
            .build();
  }



  public static EditUserDto createEditUserDtoA() {
    return new EditUserDto("PasswordPassword", "test@mail.com", "testName", "Ply");
  }

  public static QuizAttemptDto createQuizAttemptDtoA() {
    QuizAttemptDto quizAttemptDto = new QuizAttemptDto();

    quizAttemptDto.setQuizId(1L);

    quizAttemptDto.setScore(37);

    return quizAttemptDto;
  }
}