package edu.ntnu.idatt2105.quizapp.model.quiz;

import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the quiz attempt.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 */
class QuizAttemptTest {

  static Quiz quiz;
  static User user;
  static QuizAttempt quizAttempt;


  @BeforeAll
  static void beforeAll() {
    user = TestUtil.createUserA();
  }

  @BeforeEach
  void setUp() {
    quizAttempt = TestUtil.createQuizAttemptA();
    quiz = QuizModelTestUtil.createQuizA();
  }

  @Test
  void QuizAttempt_QuizAttemptConstructor_ReturnQuizAttempt() {

    //Arrange
    int expectedScore = 50;
    LocalDate expectedDate = LocalDate.of(2020, 1, 8);
    Quiz expectedQuiz = quiz;
    User expectedUser = user;

    //Act
    QuizAttempt attempt = QuizAttempt.builder()
            .quiz(quiz)
            .user(user)
            .score(expectedScore)
            .timestamp(expectedDate)
            .build();

    int actualScore = attempt.getScore();
    LocalDate actualDate = attempt.getTimestamp();
    Quiz actualQuiz = attempt.getQuiz();
    User actualUser = attempt.getUser();


    //Assert
    assertEquals(expectedScore, actualScore);
    assertEquals(expectedDate, actualDate);
    assertEquals(expectedQuiz, actualQuiz);
    assertEquals(expectedUser, actualUser);
  }

  @Test
  void QuizAttempt_QuizAttemptNoArg_ReturnQuizAttempt() {

    //Arrange
    QuizAttempt attempt = new QuizAttempt();
    int expectedScore = 50;

    //Act
    attempt.setScore(expectedScore);
    int actualScore = attempt.getScore();

    //Assert
    assertEquals(expectedScore, actualScore);
  }


  @Test
  void QuizAttempt_GetQuiz_ReturnsQuiz() {
    //Arrange
    Quiz expected = quiz;

    //Act
    quizAttempt.setQuiz(expected);
    Quiz actual = quizAttempt.getQuiz();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void QuizAttempt_GetUser_ReturnsUser() {
    //Arrange
    User expected = user;

    //Act
    User actual = quizAttempt.getUser();

    //Assert
    assertEquals(expected, actual);
  }


  @Test
  void QuizAttempt_GetScore_ReturnSora() {

    //Arrange
    int expected = 37;

    //Act
    int actual = quizAttempt.getScore();

    //Assert
    assertEquals(expected, actual);

  }

  @Test
  void QuizAttempt_GetDate_ReturnDate() {

    //Arrange
    LocalDate expected = LocalDate.of(2020, 1, 8);

    //Act
    LocalDate actual = quizAttempt.getTimestamp();

    //Assert
    assertEquals(expected, actual);
  }


  @Test
  void QuizAttempt_SetQuiz_ReturnSavedQuiz() {

    //Arrange
    Quiz expected = QuizModelTestUtil.createQuizB();

    //Act
    quizAttempt.setQuiz(expected);
    Quiz actual = quizAttempt.getQuiz();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void QuizAttempt_SetUser_ReturnSavedUser() {

    //Arrange
    User expected = TestUtil.createUserB();

    //Act
    quizAttempt.setUser(expected);
    User actual = quizAttempt.getUser();

    //Assert
    assertEquals(expected, actual);
  }


  @Test
  void QuizAttempt_SetScore_ReturnSavedScore() {

    //Arrange
    int expected = 34;

    //Act
    quizAttempt.setScore(expected);
    int actual = quizAttempt.getScore();

    //Assert
    assertEquals(expected, actual);
  }


  @Test
  void QuizAttempt_SetDate_ReturnSavedDate() {

    //Arrange
    LocalDate expected = LocalDate.of(2020, 1, 8);

    //Act
    quizAttempt.setTimestamp(expected);
    LocalDate actual = quizAttempt.getTimestamp();

    //Assert
    assertEquals(expected, actual);
  }
}
