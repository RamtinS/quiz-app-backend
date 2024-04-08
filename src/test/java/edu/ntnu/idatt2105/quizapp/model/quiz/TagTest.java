
package edu.ntnu.idatt2105.quizapp.model.quiz;

import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the tag model.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 */
class TagTest {

  Tag tag;

  @BeforeEach
  void setUp() {
    tag = TestUtil.createTagA();
  }

  @Test
  void
  Tag_TagConstructorNoArg_ReturnTag() {
    //Arrange
    String expected = "Integrals";

    //Act
    Tag testTag = new Tag();
    testTag.setDescription(expected);
    String actual = testTag.getDescription();

    //Assert
    assertEquals(expected, actual);
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
    String actual = tag.getDescription();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void Tag_SetDescription_ReturnSavedDescription() {

    //Arrange
    String expected = "Derivative";

    //Act
    tag.setDescription(expected);
    String actual = tag.getDescription();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void Tag_SetDescriptionWithNull_ReturnException() {
    assertThrows(NullPointerException.class, () ->
            tag.setDescription(null)
    );
  }
}
