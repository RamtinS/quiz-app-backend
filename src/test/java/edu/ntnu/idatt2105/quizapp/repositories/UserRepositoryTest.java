package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.TestUtil;
import edu.ntnu.idatt2105.quizapp.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  @Order(1)
  public void UserRepository_FindById_ReturnUser() {

    //Arrange
    User user = TestUtil.createUserA();

    User savedUser = userRepository.save(user);

    User foundUser = userRepository.findById(1L).get();

    assertEquals(foundUser.getUsername(), user.getUsername());
  }

  @Test
  @Order(2)
  public void UserRepository_FindByUsername_ReturnUser() {

    //Arrange
    User user = TestUtil.createUserA();
    userRepository.save(user);

    //Act
    User foundUser = userRepository.findUserByUsername(user.getUsername()).get();

    //Assert
    assertEquals(user.getUsername(), foundUser.getUsername());
  }

  @Test
  @Order(3)
  public void UserRepository_Save_ReturnSavedUser() {

    //Arrange
    User user = TestUtil.createUserA();

    //Act
    User foundUser = userRepository.save(user);

    //Assert
    assertEquals(user.getUsername(), foundUser.getUsername());
  }

  @Test
  @Order(4)
  public void UserRepository_Delete_ReturnNull() {

    //Arrange
    User user = TestUtil.createUserA();
    userRepository.save(user);

    //Act
    userRepository.delete(user);
    boolean isUserPresent = userRepository.findUserByUsername(user.getUsername()).isPresent();

    //Assert
    assertFalse(isUserPresent);
  }

  @Test
  @Order(5)
  public void UserRepository_FindByUsername_DoesNotReturnUser() {

    //Arrange
    String username = "Random Username";

    //Act
    boolean isUserPresent = userRepository.findUserByUsername(username).isPresent();

    //Assert
    assertFalse(isUserPresent);
  }



}