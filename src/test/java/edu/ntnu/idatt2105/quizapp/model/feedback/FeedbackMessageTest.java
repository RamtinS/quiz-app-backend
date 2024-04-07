package edu.ntnu.idatt2105.quizapp.model.feedback;

import edu.ntnu.idatt2105.quizapp.model.FeedbackMessage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackMessageTest {

  @Test
  void FeedbackMessage_Constructor_ReturnFeedbackMessage() {

    //Arrange
    String expectedEmail = "text@example.com";
    String expectedSurName = "Jake";
    String expectedName = "Tobias";
    String expectedContent = "Chicken in the house.";
    LocalDate expectedDate = LocalDate.of(2020, 10, 20);

    //Act
    FeedbackMessage feedbackMessage = FeedbackMessage.builder()
            .email(expectedEmail)
            .surname(expectedSurName)
            .name(expectedName)
            .content(expectedContent)
            .timestamp(expectedDate)
            .build();

    String actualEmail = feedbackMessage.getEmail();
    String actualSurname = feedbackMessage.getSurname();
    String actualName = feedbackMessage.getName();
    String actualContent = feedbackMessage.getContent();
    LocalDate actualDate = feedbackMessage.getTimestamp();

    //Asser
    assertEquals(expectedEmail, actualEmail);
    assertEquals(expectedSurName, actualSurname);
    assertEquals(expectedName, actualName);
    assertEquals(expectedContent, actualContent);
    assertEquals(expectedDate, actualDate);
  }

  @Test
  void FeedbackMessage_ConstructorWithNull_ThrowsException() {
    assertThrows(NullPointerException.class, () ->
            FeedbackMessage.builder()
                    .email(null)
                    .surname(null)
                    .name(null)
                    .content(null)
    );
  }

  @Test
  void FeedbackMessage_ConstructorNoArgs_ThrowsException() {

    //Arrange
    FeedbackMessage feedbackMessage = new FeedbackMessage();
    String expectedEmail = "test@email.no";

    //Act
    feedbackMessage.setEmail(expectedEmail);
    String actualEmail = feedbackMessage.getEmail();

    //Assert
    assertEquals(expectedEmail, actualEmail);
  }
}
