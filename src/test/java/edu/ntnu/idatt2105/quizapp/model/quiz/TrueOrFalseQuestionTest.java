package edu.ntnu.idatt2105.quizapp.model.quiz;

import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the true or false question model object.
 *
 * @version 1.0
 * @author Jeffrey Yaw Annor Tabiri
 */
class TrueOrFalseQuestionTest {

  TrueOrFalseQuestion trueOrFalseQuestion;

  @BeforeEach
  void setUp() {
    trueOrFalseQuestion = TrueOrFalseQuestion.builder()
            .questionText("Is this a question?")
            .questionIsCorrect(true)
            .build();
  }

  @Test
  void TrueOrFalseQuestion_Constructor_ReturnTrueOrFalseQuestion() {
    //Arrange
    String expectedQuestionText = "Is this a question?";
    Boolean expectedQuestionIsCorrect = true;

    //Act
    TrueOrFalseQuestion question = TrueOrFalseQuestion.builder()
            .questionText(expectedQuestionText)
            .questionIsCorrect(expectedQuestionIsCorrect)
            .quiz(QuizModelTestUtil.createQuizA())
            .build();

    String actualQuestionText = question.getQuestionText();
    Boolean actualQuestionIsCorrect = question.getQuestionIsCorrect();

    //Assert
    assertEquals(expectedQuestionText, actualQuestionText);
    assertEquals(expectedQuestionIsCorrect, actualQuestionIsCorrect);
  }

  @Test
  void TrueOrFalseQuestion_ConstructorWithNullQuestionText_ReturnException() {
    //Act and Assert
    assertThrows(NullPointerException.class, () -> TrueOrFalseQuestion.builder()
            .questionText(null)
            .questionIsCorrect(true)
            .build());
  }

  @Test
  void TrueOrFalseQuestion_ConstructorWithNoArgs_ReturnException() {
    //Arrange
    TrueOrFalseQuestion trueOrFalseQuestion = new TrueOrFalseQuestion();

    //Act
    String expectedQuestionText = "Is this a question?";
    Boolean expectedQuestionIsCorrect = true;
    trueOrFalseQuestion.setQuestionText(expectedQuestionText);
    trueOrFalseQuestion.setQuestionIsCorrect(expectedQuestionIsCorrect);
    String actualQuestionText = trueOrFalseQuestion.getQuestionText();
    Boolean actualQuestionIsCorrect = trueOrFalseQuestion.getQuestionIsCorrect();


    //Assert
    assertEquals(expectedQuestionText, actualQuestionText);
    assertEquals(expectedQuestionIsCorrect, actualQuestionIsCorrect);
  }



  @Test
  void getQuestionIsCorrect() {
    //Act
    Boolean actual = trueOrFalseQuestion.getQuestionIsCorrect();

    //Assert
    assertTrue(actual);
  }

  @Test
  void setQuestionIsCorrect() {
    //Act
    trueOrFalseQuestion.setQuestionIsCorrect(false);
    Boolean actual = trueOrFalseQuestion.getQuestionIsCorrect();

    //Assert
    assertFalse(actual);
  }
}