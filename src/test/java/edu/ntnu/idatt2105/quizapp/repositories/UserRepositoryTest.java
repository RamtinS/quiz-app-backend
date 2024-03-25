package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.TestUtil;
import edu.ntnu.idatt2105.quizapp.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the user repository.
 *
 * @version 1.0
 * @since 2024-03-25
 * @author Jytabiri
 */
@DataJpaTest
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  private User testUser;

  private boolean isSetupDone = false;

  /**
   * Arranging method to create and save a test user in database.
   * isSetupDone is boolean value used to restrict the setup to run once.
   */
  @BeforeEach
  public void setUp() {
    if (!isSetupDone) {
      testUser = userRepository.save(TestUtil.createUserA());
      isSetupDone = true;
    }
  }

  @Test
  public void UserRepository_FindById_ReturnUser() {

    //Act
    User actual = userRepository.findById(testUser.getUserId()).get();

    //Assert
    assertEquals(testUser, actual);
  }

  @Test
  public void UserRepository_FindByUsername_ReturnUser() {

    //Act
    User actual = userRepository.findUserByUsername(testUser.getUsername()).get();

    //Assert
    assertEquals(testUser, actual);
  }

  @Test
  public void UserRepository_Save_ReturnSavedUser() {

    //Arrange
    User expected = userRepository.save(TestUtil.createUserB());

    //Act
    User actual = userRepository.findUserByUsername(expected.getUsername()).get();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  public void UserRepository_Delete_ReturnNull() {

    //Arrange
    User actual = TestUtil.createUserC();
    userRepository.save(actual);

    //Act
    userRepository.delete(actual);
    boolean isUserPresent = userRepository.findUserByUsername(actual.getUsername()).isPresent();

    //Assert
    assertFalse(isUserPresent);
  }

}