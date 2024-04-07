package edu.ntnu.idatt2105.quizapp.model.quiz;

import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the category model object.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 */
class CategoryTest {

  Category category;

  @BeforeEach
  void setUp() {
    category = TestUtil.createCategoryA();
  }

  @Test
  void Category_Constructor_ReturnCategory() {
    //Arrange
    String expected = "Physics";
    Quiz expectedQuiz = QuizModelTestUtil.createQuizA();

    //Act
    Category category = Category.builder()
            .description(expected)
            .quizzes(List.of(expectedQuiz))
            .build();
    String actual = category.getDescription();
    List<Quiz> actualQuiz = category.getQuizzes();

    //Assert
    assertTrue(actualQuiz.contains(expectedQuiz));
    assertEquals(expected, actual);
  }

  @Test
  void Category_ConstructorWithNullDescription_ReturnException() {
    //Act and Assert
    assertThrows(NullPointerException.class, () -> {
      Category category = Category.builder()
              .description(null)
              .build();
    });

  }

  @Test
  void Category_ConstructorWithNoArgs_ReturnCategory() {
    Category expectedCategory = new Category();
    String expectedDescription = "Food";

    expectedCategory.setDescription(expectedDescription);
    String actualDescription = expectedCategory.getDescription();

    assertEquals(expectedDescription, actualDescription);
  }



  @Test
  void Category_GetDescription_ReturnDescription() {
    //Arrange
    String expected = "Physics";

    //Act
    String actual = category.getDescription();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void Category_GetQuizzes() {
    //Arrange
    Quiz expected = QuizModelTestUtil.createQuizA();

    //Act
    Quiz actual = category.getQuizzes().getFirst();

    //Assert
    assertEquals(expected.getName(), actual.getName());

  }

  @Test
  void Category_SetDescription_ReturnsSavedDescription() {
    String expected = "Food";

    category.setDescription(expected);
    String actual = category.getDescription();

    assertEquals(expected, actual);
  }

  @Test
  void Category_SetDescriptionNull_ThrowsException() {
    //Act and Assert
    assertThrows(NullPointerException.class, () -> {
      category.setDescription(null);
    });
  }

  @Test
  void Category_SetQuizzes_ReturnsSavedQuiz() {
    Quiz expected = QuizModelTestUtil.createQuizB();

    category.setQuizzes(List.of(expected));
    List<Quiz> actual = category.getQuizzes();

    assertTrue(actual.contains(expected));

  }

  @Test
  public void User_Hashcode_False() {
    //Arrange
    Category categoryA = TestUtil.createCategoryA();
    Category categoryB = TestUtil.createCategoryB();

    //Act
    int hashA = categoryA.hashCode();
    int hashB = categoryB.hashCode();

    //Assert
    assertNotEquals(hashA, hashB);
  }

  @Test
  public void User_Hashcode_True() {
    //Arrange
    Category categoryA = TestUtil.createCategoryA();

    //Act
    int hashA = categoryA.hashCode();
    int hashB = categoryA.hashCode();

    //Assert
    assertEquals(hashA, hashB);
  }

  @Test
  public void Equals_Null_False() {
    //Arrange
    Category categoryA = TestUtil.createCategoryA();

    //Act
    boolean result = categoryA.equals(null);

    //Assert
    assertFalse(result);
  }

  @Test
  public void Equals_SameObject_True() {
    //Arrange
    Category categoryA = TestUtil.createCategoryA();
    Category categoryB = categoryA;

    //Act
    boolean result = categoryA.equals(categoryB);

    //Assert
    assertTrue(result);
  }

  @Test
  public void Equals_DifferentObject_False() {
    //Arrange
    Category categoryA = TestUtil.createCategoryA();
    Category categoryB = TestUtil.createCategoryB();

    //Act
    boolean result = categoryA.equals(categoryB);

    //Assert
    assertFalse(result);
  }

}