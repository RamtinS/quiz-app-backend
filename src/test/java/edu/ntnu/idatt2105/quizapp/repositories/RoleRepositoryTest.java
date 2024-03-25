package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.TestUtil;
import edu.ntnu.idatt2105.quizapp.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the role repository.
 *
 * @version 1.0
 * @since 2024-03-25
 * @author Jytabiri
 */
@DataJpaTest
class RoleRepositoryTest {

  @Autowired
  private  RoleRepository roleRepository;

  private Role testRole;

  private boolean isSetupDone = false;

  /**
   * Arranging method to create and save a test role in database.
   * isSetupDone is boolean value used to restrict the setup to run once.
   */
  @BeforeEach
  public void setUp() {
    if (!isSetupDone) {
      testRole = roleRepository.save(TestUtil.createRoleA());
      isSetupDone = true;
    }
  }

  @Test
  void RoleRepository_FindByAuthority_ReturnRole() {

    //Act
    Role expectedRole = roleRepository.findByAuthority(testRole.getAuthority()).get();

    //Assert
    assertEquals(expectedRole, testRole);
  }

  @Test
  void RoleRepository_SaveRole_ReturnRole() {

    //Arrange
    Role actual = roleRepository.save(TestUtil.createRoleB());

    //Act
    Role expected  = roleRepository.findById(actual.getRoleId()).get();

    //Assert
    assertEquals(expected,actual);
  }

  @Test
  void RoleRepository_FindById_ReturnRole() {
    //Act
    Role expectedRole = roleRepository.findById(testRole.getRoleId()).get();

    //Assert
    assertEquals(expectedRole, testRole);
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
}