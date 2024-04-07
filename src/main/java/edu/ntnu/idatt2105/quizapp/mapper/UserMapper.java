package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.PublicUserInformationDto;
import edu.ntnu.idatt2105.quizapp.model.user.User;
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
  public PublicUserInformationDto mapToPublicUserInformation(@NonNull User user) {
    return PublicUserInformationDto.builder()
        .username(user.getUsername())
        .build();
  }
}
