package edu.ntnu.idatt2105.quizapp.services;

import edu.ntnu.idatt2105.quizapp.dto.PublicUserInformationDTO;
import edu.ntnu.idatt2105.quizapp.mapper.UserMapper;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for user-related operations.
 *
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  /**
   * Finds a list of public profile DTOs based on the search parameter.
   * @param searchString the search parameter.
   * @param pageable the pageable used to find a specified page.
   * @return a list of public profile DTOs based on the search parameter.
   */
  public List<PublicUserInformationDTO> findPublicProfilesFromUsername(String searchString, Pageable pageable) {
    return userRepository.findAllByUsernameContainingIgnoreCase(
        searchString, pageable).stream().map(userMapper::mapToPublicUserInformation).toList();
  }




}
