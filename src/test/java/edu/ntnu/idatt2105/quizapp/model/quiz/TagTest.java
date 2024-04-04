package edu.ntnu.idatt2105.quizapp.model.quiz;

import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the tag model.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @since 4/4/2024
 * @version 1.0
 */
class TagTest {

  Tag testTag;

  @BeforeEach
  void setUp() {
    testTag = TestUtil.createTagA();
  }

  @Test
  void Tag_TagConstructor_ReturnTag() {

    //Arrange
    String expected = "Integrals";

    //Act
    Tag testTag = Tag.builder()
            .description(expected)
            .build();
    String actual = testTag.getDescription();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void Tag_TagConstructorWithNull_ReturnException() {
    assertThrows(NullPointerException.class, () ->
            Tag.builder()
                    .description(null)
                    .build()
    );
  }

  @Test
  void Tag_GetDescription_ReturnDescription() {

    //Arrange
    String expected = "Integrals";

    //Act
    String actual = testTag.getDescription();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void Tag_SetDescription_ReturnSavedDescription() {

    //Arrange
    String expected = "Derivative";

    //Act
    testTag.setDescription(expected);
    String actual = testTag.getDescription();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void Tag_SetDescriptionWithNull_ReturnException() {
    assertThrows(NullPointerException.class, () ->
            testTag.setDescription(null)
    );
  }
}