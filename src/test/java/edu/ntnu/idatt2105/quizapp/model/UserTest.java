package edu.ntnu.idatt2105.quizapp.model;

import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

  void User_UserConstructor_ReturnUser() {
    //Arrange
    String expectedUsername = "Alice";
    String expectedPassword = "Alice123";
    String expectedEmail = "alice@example.com";
    String expectedName = "Alice";
    String expectedSurName = "Johnson";
    Role expectedRole = Role.ADMIN;

    //Act
    User actual = TestUtil.createUserB();

    // Assert
    assertEquals(expectedUsername, actual.getUsername());
    assertEquals(expectedPassword, actual.getPassword());
    assertEquals(expectedEmail, actual.getEmail());
    assertEquals(expectedName, actual.getName());
    assertEquals(expectedSurName, actual.getSurName());
    assertEquals(expectedRole, actual.getRole());
  }

  @Test
  public void User_UserConstructWithNull_ThrowsException() {
    assertThrows(NullPointerException.class, () ->
            User.builder()
                    .username(null)
                    .password(null)
    );
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
  void User_GetRole_ReturnRole() {
    //Arrange
    Role expected = Role.USER;

    //Act
    Role actual = testUser.getRole();

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

  @Test
  void User_SetRole_ReturnSavedRole() {
    //Arrange
    Role expected = Role.ADMIN;

    //Act
    testUser.setRole(expected);
    Role actual = testUser.getRole();

    //Assert
    assertEquals(expected, actual);
  }

}