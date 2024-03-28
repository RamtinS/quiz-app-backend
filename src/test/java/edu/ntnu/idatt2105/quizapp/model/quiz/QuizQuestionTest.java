package edu.ntnu.idatt2105.quizapp.model.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the {@link edu.ntnu.idatt2105.quizapp.model.quiz.QuizQuestion} class.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-25
 */
public class QuizQuestionTest {
  @Test
  public void Equals_EqualQuestions_True() {
    //Arrange
    QuizQuestion quizQuestionA = QuizModelTestUtil.createQuizQuestionA();
    QuizQuestion quizQuestionB = QuizModelTestUtil.createQuizQuestionA();

    //Act
    boolean result = quizQuestionA.equals(quizQuestionB);

    //Assert
    assertTrue(result);
  }

  @Test
  public void Equals_NotEqualQuestions_False() {
    //Arrange
    QuizQuestion quizQuestionA = QuizModelTestUtil.createQuizQuestionA();
    QuizQuestion quizQuestionB = QuizModelTestUtil.createQuizQuestionB();

    //Act
    boolean result = quizQuestionA.equals(quizQuestionB);

    //Assert
    assertFalse(result);
  }

  @Test
    public void Equals_Null_False() {
        //Arrange
        QuizQuestion quizQuestionA = QuizModelTestUtil.createQuizQuestionA();
        QuizQuestion quizQuestionB = null;

        //Act
        boolean result = quizQuestionA.equals(quizQuestionB);

        //Assert
        assertFalse(result);
    }

    @Test
    public void Equals_SameObject_True() {
        //Arrange
        QuizQuestion quizQuestionA = QuizModelTestUtil.createQuizQuestionA();
        QuizQuestion quizQuestionB = quizQuestionA;

        //Act
        boolean result = quizQuestionA.equals(quizQuestionB);

        //Assert
        assertTrue(result);
    }

    @Test
    public void HashCode_SameObject_True() {
        //Arrange
        QuizQuestion quizQuestionA = QuizModelTestUtil.createQuizQuestionA();
        QuizQuestion quizQuestionB = quizQuestionA;

        //Act
        boolean result = quizQuestionA.hashCode() == quizQuestionB.hashCode();

        //Assert
        assertTrue(result);
    }

@Test
  public void SetQuestionText_TextIsHello_True() {
    //Arrange
    QuizQuestion quizQuestion = new QuizQuestion();
    String newQuestionText = "New question text";

    //Act
    quizQuestion.setQuestionText(newQuestionText);

    //Assert
    assertNotEquals(QuizModelTestUtil.createQuizQuestionA().getQuestionText(), quizQuestion.getQuestionText());
  }

  @Test
  public void SetAnswers_AnswersIsNotEmpty_True() {
    //Arrange
    QuizQuestion quizQuestion = new QuizQuestion();


    Answer answer = QuizModelTestUtil.createAnswerA();
    List<Answer> answers  = List.of(answer);


    //Act
    quizQuestion.setAnswers(answers);

    //Assert
    assertEquals(quizQuestion.getAnswers(), answers);
  }

  public void SetQuiz_QuizIsNotNull_True() {
    //Arrange
    QuizQuestion quizQuestion = new QuizQuestion();
    Quiz quiz = QuizModelTestUtil.createQuizA();

    //Act
    quizQuestion.setQuiz(quiz);

    //Assert
    assertEquals(quizQuestion.getQuiz(), quiz);
  }



}
