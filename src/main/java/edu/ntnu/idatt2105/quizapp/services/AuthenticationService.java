package edu.ntnu.idatt2105.quizapp.services;

import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.repositories.RoleRepository;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * AuthenticationService encapsulates the logic for registering new users
 * and authenticating existing users within
 * backend. It uses repositories to perform user-related operations in database,
 * and utilizes spring boot security for user authentication.
 *
 * @author Jytabiri
 * @version 1.0
 * @since 2024-03-22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

  //CRUD operations on user models.
  private final UserRepository userRepository;

  //CRUD operations on role models.
  private final RoleRepository roleRepository;

  //Authenticates login requests.
  private final AuthenticationManager authenticationManager;

  //Password encoder to hash passwords in database
  private final BCryptPasswordEncoder passwordEncoder;

  /**
   * Register method which contains logic to create a user from the given request.
   *
   * @param username is to be the username of the user object.
   * @param password is to be the password of the user object.
   * @return if authentication is successful, it creates the user and returns it.
   */
  public User registerUser(String username, String password) {
    String encodedPassword = passwordEncoder.encode(password);
    Role userRole = roleRepository.findByAuthority("USER").get();

    Set<Role> authorities = new HashSet<>();

    authorities.add(userRole);

    User user = User.builder()
            .username(username)
            .password(encodedPassword)
            .authorities(authorities)
            .build();

    //TODO should return a acceptable request of some kind.
    return userRepository.save(user);
  }


  /**
   * Login method which contains logic to authenticate a login request.
   *
   * @param username is the username to be checked and authenticated
   * @param password is the password to be checked and authenticated
   * @return if authentication is successful. It retrieves and returns the user from the database.
   */
  public User loginUser(String username, String password) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

    //TODO should return a LoginRequestDTO with a JWT token.
    return userRepository.findUserByUsername(username).get();
  }

}
