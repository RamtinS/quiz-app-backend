
package edu.ntnu.idatt2105.quizapp.model.quiz;

import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the quiz model object.
 *
 * @version 1.0
 * @author Jeffrey Yaw Annor Tabiri
 */
public class QuizTest {

  Quiz quiz;
  @BeforeEach
  void setUp() {
    quiz = QuizModelTestUtil.createQuizA();
  }

  @Test
  void Quiz_ConstructorNoArgs_ReturnQuiz() {
    //Arrange
    Quiz quiz = new Quiz();

    //Act
    String expectedName = "Quiz";
    quiz.setName(expectedName);
    String actualName = quiz.getName();

    //Assert
    assertEquals(expectedName, actualName);
  }

  @Test
  void Equals_EqualQuizzes_True() {
    //Arrange
    Quiz quizA = QuizModelTestUtil.createQuizA();
    Quiz quizB = quizA;

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

  @Test
  void Quiz_HashCode_False() {
    //Arrange
    Quiz quizA = QuizModelTestUtil.createQuizA();
    Quiz quizB = QuizModelTestUtil.createQuizB();

    //Act
    int hashA = quizA.hashCode();
    int hashB = quizB.hashCode();

    //Assert
    assertNotEquals(hashA, hashB);
  }
  @Test
  void Quiz_Hashcode_True() {
    //Arrange
    Quiz quizA = QuizModelTestUtil.createQuizA();

    //Act
    int hashA = quizA.hashCode();
    int hashB = quizA.hashCode();

    //Assert
    assertEquals(hashA, hashB);
  }

  @Test
  void Quiz_GetId_ReturnId() {
    //Arrange
    Long expected = 1L;

    //Act
    quiz.setId(expected);
    Long actual = quiz.getId();

    //Assert
    assertEquals(actual, expected);
  }

  @Test
  void Quiz_GetName_ReturnName() {
    //Arrange
    String expected = "Quiz";

    //Act
    quiz.setName(expected);
    String actual = quiz.getName();

    //Assert
    assertEquals(actual, expected);
  }

  @Test
  void Quiz_GetDescription_ReturnDescription() {
    //Arrange
    String expected = "This is a quiz";

    //Act
    quiz.setDescription(expected);
    String actual = quiz.getDescription();

    //Assert
    assertEquals(actual, expected);
  }

  @Test
  void Quiz_GetQuestions_ReturnQuestions() {
    //Arrange
    Question expected = QuizModelTestUtil.createQuizQuestionA();

    //Act
    quiz.setQuestions(List.of(expected));
    List<Question> actual = quiz.getQuestions();

    //Assert
    assertTrue(actual.contains(expected));
  }

  @Test
  void Quiz_GetAuthor_ReturnAuthor() {
    //Arrange
    User expected = TestUtil.createUserA();

    //Act
    quiz.setAuthor(expected);
    User actual = quiz.getAuthor();

    //Assert
    assertEquals(actual, expected);
  }

  @Test
  void Quiz_GetCategory_ReturnCategory() {
    //Arrange
    Category expected = TestUtil.createCategoryA();

    //Act
    quiz.setCategory(expected);
    Category actual = quiz.getCategory();

    //Assert
    assertEquals(actual, expected);
  }

  @Test
  void Quiz_GetTags_ReturnTags() {
    //Arrange
    Tag expected = TestUtil.createTagA();

    //Act
    quiz.setTags(List.of(expected));
    List<Tag> actual = quiz.getTags();

    //Assert
    assertTrue(actual.contains(expected));
  }

  @Test
  void Quiz_GetOpen_ReturnIsOpen() {
    //Arrange
    boolean expected = true;

    //Act
    quiz.setIsOpen(true);
    boolean actual = quiz.getIsOpen();

    //Assert
    assertTrue(actual);
  }

  @Test
  void Quiz_SetId_SetsCorrectId() {
    //Arrange
    Long expected = 1L;

    //Act
    quiz.setId(expected);
    Long actual = quiz.getId();

    //Assert
    assertEquals(actual, expected);
  }

  @Test
  void Quiz_SetName_SetsCorrectName() {
    //Arrange
    String expected = "Quiz";

    //Act
    quiz.setName(expected);
    String actual = quiz.getName();

    //Assert
    assertEquals(actual, expected);
  }

  @Test
  void Quiz_SetDescription_SetsCorrectDescriptions() {
    //Arrange
    String expected = "This is a quiz";

    //Act
    quiz.setDescription(expected);
    String actual = quiz.getDescription();

    //Assert
    assertEquals(actual, expected);
  }

  @Test
  void Quiz_SetQuestions_SetsCorrectQuestions() {
    //Arrange
    Question expected = QuizModelTestUtil.createQuizQuestionA();

    //Act
    quiz.setQuestions(List.of(expected));
    List<Question> actual = quiz.getQuestions();

    //Assert
    assertTrue(actual.contains(expected));
  }

  @Test
  void Quiz_SetAuthor_SetsCorrectAuthor() {
    //Arrange
    User expected = TestUtil.createUserA();

    //Act
    quiz.setAuthor(expected);
    User actual = quiz.getAuthor();

    //Assert
    assertEquals(actual, expected);
  }

  @Test
  void Quiz_SetCategory_SetsCorrectCategory() {
    //Arrange
    Category expected = TestUtil.createCategoryA();

    //Act
    quiz.setCategory(expected);
    Category actual = quiz.getCategory();

    //Assert
    assertEquals(actual, expected);
  }

  @Test
  void Quiz_Set_Tags() {
    //Arrange
    Tag expected = TestUtil.createTagA();

    //Act
    quiz.setTags(List.of(expected));
    List<Tag> actual = quiz.getTags();

    //Assert
    assertTrue(actual.contains(expected));
  }

  @Test
  void Quiz_SetIsOpen_ReturnOpen() {
    //Arrange
    boolean expected = true;

    //Act
    quiz.setIsOpen(true);
    boolean actual = quiz.getIsOpen();

    //Assert
    assertTrue(actual);
  }

}
