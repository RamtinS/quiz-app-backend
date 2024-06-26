
package edu.ntnu.idatt2105.quizapp.model.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the {@link MultipleChoiceQuestion} class.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
public class MultipleChoiceQuestionTest {

  @Test
  void MultiChoiceQuestion_Constructor_ReturnMultiChoiceQuestion() {
    //Arrange
    String expectedQuestionText = "What is the capital of Norway?";
    Answer expectedAnswer = QuizModelTestUtil.createAnswerA();
    List<Answer> expectedAnswers = List.of(expectedAnswer);
    Quiz expectedQuiz = QuizModelTestUtil.createQuizA();

    //Act
    MultipleChoiceQuestion multipleChoiceQuestion = MultipleChoiceQuestion.builder()
            .questionText(expectedQuestionText)
            .answers(expectedAnswers)
            .quiz(expectedQuiz)
            .build();
    String actualQuestionText = multipleChoiceQuestion.getQuestionText();
    List<Answer> actualAnswers = multipleChoiceQuestion.getAnswers();
    Quiz actualQuiz = multipleChoiceQuestion.getQuiz();

    //Assert
    assertTrue(actualAnswers.contains(expectedAnswer));
    assertEquals(expectedQuestionText, actualQuestionText);
    assertEquals(expectedQuiz, actualQuiz);
  }

  @Test
  public void Equals_EqualQuestions_True() {
    //Arrange
    MultipleChoiceQuestion multipleChoiceQuestionA = QuizModelTestUtil.createQuizQuestionA();
    MultipleChoiceQuestion multipleChoiceQuestionB = multipleChoiceQuestionA;

    //Act
    boolean result = multipleChoiceQuestionA.equals(multipleChoiceQuestionB);

    //Assert
    assertTrue(result);
  }

  @Test
  public void Equals_NotEqualQuestions_False() {
    //Arrange
    MultipleChoiceQuestion multipleChoiceQuestionA = QuizModelTestUtil.createQuizQuestionA();
    MultipleChoiceQuestion multipleChoiceQuestionB = QuizModelTestUtil.createQuizQuestionB();

    //Act
    boolean result = multipleChoiceQuestionA.equals(multipleChoiceQuestionB);

    //Assert
    assertFalse(result);
  }

  @Test
  public void Equals_Null_False() {
    //Arrange
    MultipleChoiceQuestion multipleChoiceQuestionA = QuizModelTestUtil.createQuizQuestionA();
    MultipleChoiceQuestion multipleChoiceQuestionB = null;

    //Act
    boolean result = multipleChoiceQuestionA.equals(multipleChoiceQuestionB);

    //Assert
    assertFalse(result);
  }

  @Test
  public void Equals_SameObject_True() {
    //Arrange
    MultipleChoiceQuestion multipleChoiceQuestionA = QuizModelTestUtil.createQuizQuestionA();
    MultipleChoiceQuestion multipleChoiceQuestionB = multipleChoiceQuestionA;

    //Act
    boolean result = multipleChoiceQuestionA.equals(multipleChoiceQuestionB);

    //Assert
    assertTrue(result);
  }

  @Test
  public void HashCode_SameObject_True() {
    //Arrange
    MultipleChoiceQuestion multipleChoiceQuestionA = QuizModelTestUtil.createQuizQuestionA();
    MultipleChoiceQuestion multipleChoiceQuestionB = multipleChoiceQuestionA;

    //Act
    boolean result = multipleChoiceQuestionA.hashCode() == multipleChoiceQuestionB.hashCode();

    //Assert
    assertTrue(result);
  }

  @Test
  public void SetQuestionText_TextIsHello_True() {
    //Arrange
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
    String newQuestionText = "New question text";

    //Act
    multipleChoiceQuestion.setQuestionText(newQuestionText);

    //Assert
    assertNotEquals(QuizModelTestUtil.createQuizQuestionA().getQuestionText(), multipleChoiceQuestion.getQuestionText());
  }

  @Test
  public void SetAnswers_AnswersIsNotEmpty_True() {
    //Arrange
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();


    Answer answer = QuizModelTestUtil.createAnswerA();
    List<Answer> answers  = List.of(answer);


    //Act
    multipleChoiceQuestion.setAnswers(answers);

    //Assert
    assertEquals(multipleChoiceQuestion.getAnswers(), answers);
  }

  @Test
  public void SetQuiz_QuizIsNotNull_True() {
    //Arrange
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
    Quiz quiz = QuizModelTestUtil.createQuizA();

    //Act
    multipleChoiceQuestion.setQuiz(quiz);

    //Assert
    assertEquals(multipleChoiceQuestion.getQuiz(), quiz);
  }



}
