package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.TestUtil;
import edu.ntnu.idatt2105.quizapp.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the role repository.
 */
@DataJpaTest
class RoleRepositoryTest {

  @Autowired
  private  RoleRepository roleRepository;

  @Test
  void RoleRepository_FindByAuthority_ReturnRole() {

    //Arrange
    Role actualRole = TestUtil.createRoleA();
    roleRepository.save(actualRole);

    //Act
    Role expectedRole = roleRepository.findByAuthority("USER").get();

    //Assert
    assertEquals(expectedRole.getAuthority(), actualRole.getAuthority());
  }

  @Test
  void RoleRepository_SaveRole_ReturnRole() {

    //Arrange
    Role actualRole = TestUtil.createRoleA();

    //Act
    roleRepository.save(actualRole);

    Role expectedRole = roleRepository.findByAuthority(actualRole.getAuthority()).get();

    //Assert
    assertNotNull(expectedRole);
    assertEquals(expectedRole.getAuthority(), actualRole.getAuthority());
  }

  @Test
  void RoleRepository_FindById_ReturnRole() {

    //Arrange
    Role actualRole = TestUtil.createRoleA();
    Long actualRoleId = roleRepository.save(actualRole).getRoleId();

    //Act
    Role expectedRole = roleRepository.findById(actualRole.getRoleId()).get();

    //Assert
    assertEquals(expectedRole, actualRole);
  }

  @Test
  void RoleRepository_DeleteRole_ReturnNull() {

    //Arrange
    Role testRole = TestUtil.createRoleA();
    Role actualRole = roleRepository.save(testRole);

    //Act
    roleRepository.delete(actualRole);
    boolean isRolePresent = roleRepository.findById(actualRole.getRoleId()).isPresent();

    //Assert
    assertFalse(isRolePresent);
  }

}