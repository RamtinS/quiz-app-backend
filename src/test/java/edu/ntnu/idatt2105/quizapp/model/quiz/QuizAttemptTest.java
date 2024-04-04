package edu.ntnu.idatt2105.quizapp.model.quiz;

import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuizAttemptTest {

  static Quiz quiz;
  static User user;
  static QuizAttempt quizAttempt;


  @BeforeAll
  static void beforeAll() {
    quiz = QuizModelTestUtil.createQuizA();
    user = TestUtil.createUserA();
  }

  @BeforeEach
  void setUp() {
    quizAttempt = TestUtil.createQuizAttemptA();
  }

  @Test
  void QuizAttempt_GetQuiz_ReturnsQuiz() {
    //Arrange
    Quiz expected = quiz;

    //Act
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
  void getScore() {
  }

  @Test
  void getAttemptDate() {
  }

  @Test
  void setQuiz() {
  }

  @Test
  void setUser() {
  }

  @Test
  void setScore() {
  }

  @Test
  void setAttemptDate() {
  }
}