package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.TestUtil;
import edu.ntnu.idatt2105.quizapp.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the role repository.
 *
 * @author Jytabiri
 * @version 1.0
 * @since 2024-03-25
 */
@SpringBootTest
class RoleRepositoryTest {

  @Autowired
  private RoleRepository roleRepository;

  @Test
  void RoleRepository_SaveRole_ReturnRole() {

    //Arrange
    Role actual = roleRepository.save(TestUtil.createRoleB());

    //Act
    Role expected = roleRepository.findById(actual.getRoleId()).get();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void RoleRepository_FindById_ReturnRole() {

    //Arrange
    Role expected = roleRepository.save(TestUtil.createRoleA());

    //Act
    Role actual = roleRepository.findById(expected.getRoleId()).get();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void RoleRepository_DeleteRole_ReturnNull() {
    //Arrange
    Role expected = roleRepository.save(TestUtil.createRoleC());

    //Act
    roleRepository.delete(expected);
    boolean isRolePresent = roleRepository.findById(expected.getRoleId()).isPresent();

    //Assert
    assertFalse(isRolePresent);
  }

  @Test
  void RoleRepository_FindByAuthority_ReturnRole() {

    //Arrange
    Role expected = roleRepository.save(TestUtil.createRoleD());

    //Act
    Role actual = roleRepository.findByAuthority(expected.getAuthority()).get();

    //Assert
    assertEquals(expected, actual);
  }
}