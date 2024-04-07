package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.PublicUserInformationDTO;
import edu.ntnu.idatt2105.quizapp.model.User;
import lombok.NonNull;
import org.springframework.stereotype.Component;

/**
 * Mapper class for mapping User objects to PublicUserInformationDTO objects.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Component
public class UserMapper {

  /**
   * Maps a User object to a PublicUserInformationDTO object containing public user information.
   *
   * @param user The User object to map.
   * @return The mapped PublicUserInformationDTO object.
   * @throws NullPointerException if the provided user is null
   */
  public PublicUserInformationDTO mapToPublicUserInformation(@NonNull User user) {
    return PublicUserInformationDTO.builder()
        .username(user.getUsername())
        .build();
  }
}
