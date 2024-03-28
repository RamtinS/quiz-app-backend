package edu.ntnu.idatt2105.quizapp.model.quiz;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.Test;

public class QuizTest {
  @Test
  public void Equals_EqualQuizzes_True() {
    //Arrange
    Quiz quizA = QuizModelTestUtil.createQuizA();
    Quiz quizB = QuizModelTestUtil.createQuizA();

    //Act
    boolean result = quizA.equals(quizB);

    //Assert
    assertTrue(result);
  }

  @Test
  public void Equals_NotEqualQuizzes_False() {
    //Arrange
    Quiz quizA = QuizModelTestUtil.createQuizA();
    Quiz quizB = QuizModelTestUtil.createQuizB();

    //Act
    boolean result = quizA.equals(quizB);

    //Assert
    assertFalse(result);
  }

  @Test
  public void Equals_Null_False() {
    //Arrange
    Quiz quizA = QuizModelTestUtil.createQuizA();
    Quiz quizB = null;

    //Act
    boolean result = quizA.equals(quizB);

    //Assert
    assertFalse(result);
  }

  @Test
  public void Equals_SameObject_True() {
    //Arrange
    Quiz quizA = QuizModelTestUtil.createQuizA();
    Quiz quizB = quizA;

    //Act
    boolean result = quizA.equals(quizB);

    //Assert
    assertTrue(result);
  }

  @Test
  public void Equals_DifferentObject_False() {
    //Arrange
    Quiz quizA = QuizModelTestUtil.createQuizA();
    Object object = new Object();

    //Act
    boolean result = quizA.equals(object);

    //Assert
    assertFalse(result);
  }
}
