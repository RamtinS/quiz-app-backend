package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.PublicUserInformationDto;
import edu.ntnu.idatt2105.quizapp.model.user.User;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

  @Test
  void UserMapper_MapToPublicUserInformation_ReturnPublicUserInformationDTO() {
    // Arrange
    User user = TestUtil.createUserA();
    UserMapper userMapper = new UserMapper();

    // Act
    PublicUserInformationDto publicUserInformationDTO = userMapper.mapToPublicUserInformation(user);

    // Assert
    assertEquals(user.getUsername(), publicUserInformationDTO.getUsername());
  }
}