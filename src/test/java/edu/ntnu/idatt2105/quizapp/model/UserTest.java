package edu.ntnu.idatt2105.quizapp.model;

import edu.ntnu.idatt2105.quizapp.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the user model.
 *
 * @author Jytabiri
 * @version 1.0
 * @since 2024-03-25
 */
class UserTest {
  User testUser;

  /**
   * Arrange a testUser for every method.
   */
  @BeforeEach
  void setUp() {
    testUser = TestUtil.createUserA();
  }

  @Test
  void User_GetUsername_ReturnUsername() {

    //Arrange
    String expected = "Mark";

    //Act
    String actual = testUser.getUsername();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void User_GetPassword_ReturnPassword() {
    //Arrange
    String expected = "PasswordPassword";

    //Act
    String actual = testUser.getPassword();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void User_GetEmail_ReturnEmail() {
    //Arrange
    String expected = "test@mail.com";

    //Act
    String actual = testUser.getEmail();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void User_GetName_ReturnName() {
    //Arrange
    String expected = "testName";

    //Act
    String actual = testUser.getName();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void User_GetSurname_ReturnSurname() {
    //Arrange
    String expected = "Ply";

    //Act
    String actual = testUser.getSurName();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void User_SetUsername_ReturnSavedUsername() {
    //Arrange
    String expected = "Draven";

    //Act
    testUser.setUsername("Draven");
    String actual = testUser.getUsername();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void User_SetUsernameToNull_ReturnException() {

    //Arrange
    String expected = "username is marked non-null but is null";

    //Act
    Exception actual = assertThrows(Exception.class, () -> {
      testUser.setUsername(null);
    });

    //Assert
    assertEquals(expected, actual.getMessage());
  }


  @Test
  void User_SetPassword_ReturnSavedPassword() {
    //Arrange
    String expected = "newPassword";

    //Act
    testUser.setPassword("newPassword");
    String actual = testUser.getPassword();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void User_SetPasswordToNull_ReturnException() {
    //Arrange
    String expected = "password is marked non-null but is null";

    //Act
    Exception actual = assertThrows(Exception.class, () -> {
      testUser.setPassword(null);
    });

    //Assert
    assertEquals(expected, actual.getMessage());

  }

  @Test
  void User_SetEmail_ReturnSavedEmail() {
    //Arrange
    String expected = "test@Email.com";

    //Act
    testUser.setEmail("test@Email.com");
    String actual = testUser.getEmail();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void User_SetName_ReturnSavedName() {
    //Arrange
    String expected = "testName";

    //Act
    testUser.setName("testName");
    String actual = testUser.getName();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void User_SetSurname_ReturnSavedSurname() {
    //Arrange
    String expected = "Timothy";

    //Act
    testUser.setSurName("Timothy");
    String actual = testUser.getSurName();

    //Assert
    assertEquals(expected, actual);
  }
}