package edu.ntnu.idatt2105.quizapp.services;

import edu.ntnu.warehouseapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for user-related operations and integrating
 * with Spring Security's UserDetailsService.
 * This service provides methods to load user details by username
 * and is used for authentication and authorization.
 *
 * @author Jytabiri
 * @version 1.0
 * @since 2024-03-22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("Request currently going through userDetails");

    return userRepository.findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User is not valid"));
  }

}
