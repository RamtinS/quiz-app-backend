package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import edu.ntnu.idatt2105.quizapp.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the user repository.
 *
 * @author Jytabiri
 * @version 1.0
 * @since 2024-03-25
 */
@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void UserRepository_FindById_ReturnUser() {
    //Arrange
    User expected = userRepository.save(TestUtil.createUserA());

    //Act
    User actual = userRepository.findById(expected.getUserId()).get();

    //Assert
    assertEquals(expected.getUserId(), actual.getUserId());
    assertEquals(expected.getUsername(), actual.getUsername());
  }

  @Test
  public void UserRepository_FindByUsername_ReturnUser() {

    //Arrange
    User expected = userRepository.save(TestUtil.createUserD());

    //Act
    User actual = userRepository.findUserByUsernameIgnoreCase(expected.getUsername()).get();

    //Assert
    assertEquals(expected.getUserId(), actual.getUserId());
    assertEquals(expected.getUsername(), actual.getUsername());
  }

  @Test
  public void UserRepository_Save_ReturnSavedUser() {

    //Arrange
    User expected = userRepository.save(TestUtil.createUserB());

    //Act
    User actual = userRepository.findUserByUsernameIgnoreCase(expected.getUsername()).get();

    //Assert
    assertEquals(expected.getUsername(), actual.getUsername());
    assertEquals(expected.getUserId(), actual.getUserId());
  }

  @Test
  public void UserRepository_Delete_ReturnNull() {

    //Arrange
    User actual = TestUtil.createUserC();
    userRepository.save(actual);

    //Act
    userRepository.delete(actual);
    boolean isUserPresent = userRepository.findUserByUsernameIgnoreCase(actual.getUsername()).isPresent();

    //Assert
    assertFalse(isUserPresent);
  }

}