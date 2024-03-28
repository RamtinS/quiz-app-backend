package edu.ntnu.idatt2105.quizapp.model.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.Test;


public class AnswerTest {


  @Test
  public void Equals_EqualAnswers_True() {
    //Arrange
    Answer answerA = QuizModelTestUtil.createAnswerA();
    Answer answerB = QuizModelTestUtil.createAnswerA();

    //Act
    boolean result = answerA.equals(answerB);

    //Assert
    assertTrue(result);
  }

  @Test
  public void Equals_NotEqualAnswers_False() {
    //Arrange
    Answer answerA = QuizModelTestUtil.createAnswerA();
    Answer answerB = QuizModelTestUtil.createAnswerB();

    //Act
    boolean result = answerA.equals(answerB);

    //Assert
    assertFalse(result);
  }

  @Test
  public void Equals_Null_False() {
    //Arrange
    Answer answerA = QuizModelTestUtil.createAnswerA();
    Answer answerB = null;

    //Act
    boolean result = answerA.equals(answerB);

    //Assert
    assertFalse(result);
  }

  @Test
  public void Equals_SameObject_True() {
    //Arrange
    Answer answerA = QuizModelTestUtil.createAnswerA();
    Answer answerB = answerA;

    //Act
    boolean result = answerA.equals(answerB);

    //Assert
    assertEquals(answerA, answerB);
  }

  @Test
  public void HashCode_SameObject_True() {
    //Arrange
    Answer answerA = QuizModelTestUtil.createAnswerA();
    Answer answerB = answerA;

    //Act
    int hashA = answerA.hashCode();
    int hashB = answerB.hashCode();

    //Assert
    assertEquals(hashA, hashB);
  }

  @Test
  public void HashCode_EqualObjects_True() {
    //Arrange
    Answer answerA = QuizModelTestUtil.createAnswerA();
    Answer answerB = QuizModelTestUtil.createAnswerA();

    //Act
    int hashA = answerA.hashCode();
    int hashB = answerB.hashCode();

    //Assert
    assertEquals(hashA, hashB);
  }

  @Test
  public void HashCode_NotEqualObjects_False() {
    //Arrange
    Answer answerA = QuizModelTestUtil.createAnswerA();
    Answer answerB = QuizModelTestUtil.createAnswerB();

    //Act
    int hashA = answerA.hashCode();
    int hashB = answerB.hashCode();

    //Assert
    assertNotEquals(hashA, hashB);
  }

    @Test
  public void GetAnswerText_AnswerTextIsHello_Hello() {
    //Arrange
    Answer answerA = Answer.builder()
        .answerText("Hello")
        .build();
    String expected = "Hello";

    //Act
    String result = answerA.getAnswerText();


    //Assert
    assertEquals(result, expected);
  }

  @Test
  public void GetIsCorrect_IsCorrect_True() {
    //Arrange
    Answer answerA = new Answer();
    answerA.setIsCorrect(true);
    boolean expected = true;

    //Act
    boolean result = answerA.getIsCorrect();

    //Assert
    assertEquals(result, expected);
  }

  @Test
  public void GetId_IdIs1_1() {
    //Arrange
    Answer answerA = new Answer();
    answerA.setId(1L);
    Long expected = 1L;

    //Act
    Long result = answerA.getId();

    //Assert
    assertEquals(result, expected);
  }


}
