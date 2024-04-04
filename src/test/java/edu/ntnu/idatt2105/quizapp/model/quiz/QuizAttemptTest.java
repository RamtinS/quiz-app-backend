package edu.ntnu.idatt2105.quizapp.model.quiz;

import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the quiz attempt.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @since 4/4/2024
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
    Date expectedDate = new Date(4949L);
    Quiz expectedQuiz = quiz;
    User expectedUser = user;

    //Act
    QuizAttempt attempt = QuizAttempt.builder()
            .quiz(quiz)
            .user(user)
            .score(expectedScore)
            .attemptDate(expectedDate)
            .build();

    int actualScore = attempt.getScore();
    Date actualDate = attempt.getAttemptDate();
    Quiz actualQuiz = attempt.getQuiz();
    User actualUser = attempt.getUser();


    //Assert
    assertEquals(expectedScore, actualScore);
    assertEquals(expectedDate, actualDate);
    assertEquals(expectedQuiz, actualQuiz);
    assertEquals(expectedUser, actualUser);
  }

  @Test
  void QuizAttempt_QuizAttemptConstructor_ReturnException() {
    assertThrows(NullPointerException.class, () -> {
      QuizAttempt.builder()
              .quiz(null)
              .user(null)
              .score(0)
              .attemptDate(null)
              .build();
    });
  }

  @Test
  void QuizAttempt_GetQuiz_ReturnsQuiz() {
    //Arrange
    Quiz expected = quiz;

    //Act
    quizAttempt.setQuiz(expected);
    Quiz actual = quizAttempt.getQuiz();

    //Assert
    assertTrue(expected.equals(actual));
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
    Date expected = new Date(420520L);

    //Act
    Date actual = quizAttempt.getAttemptDate();

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
  void QuizAttempt_SetQuizWithNull_ReturnException() {
    assertThrows(NullPointerException.class, () ->
            quizAttempt.setQuiz(null)
    );
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
  void QuizAttempt_SetUserWithNull_ReturnException() {
    assertThrows(NullPointerException.class, () ->
            quizAttempt.setUser(null)
    );
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
  void QuizAttempt_SetScoreWithNull_ReturnException() {
    assertThrows(NullPointerException.class, () ->
            quizAttempt.setQuiz(null)
    );
  }

  @Test
  void QuizAttempt_SetDate_ReturnSavedDate() {

    //Arrange
    Date expected = new Date(404040L);

    //Act
    quizAttempt.setAttemptDate(expected);
    Date actual = quizAttempt.getAttemptDate();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void QuizAttempt_SetDateWithNull_ReturnException() {
    assertThrows(NullPointerException.class, () ->
            quizAttempt.setQuiz(null)
    );
  }

}