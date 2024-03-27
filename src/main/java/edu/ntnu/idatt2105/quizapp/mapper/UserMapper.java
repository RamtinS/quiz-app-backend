package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.PublicUserInformationDTO;
import edu.ntnu.idatt2105.quizapp.model.User;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

/**
 * Mapper class for mapping User objects to PublicUserInformationDTO objects.
 *
 * @version 1.0
 * @since 2024-03-27
 */
@Component
public class UserMapper {
  public PublicUserInformationDTO mapToPublicUserInformation(User user) {
    return PublicUserInformationDTO.builder()
        .username(user.getUsername())
        .build();
  }
}
