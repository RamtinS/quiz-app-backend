package edu.ntnu.idatt2105.quizapp.services;

import edu.ntnu.idatt2105.quizapp.dto.AuthenticationDto;
import edu.ntnu.idatt2105.quizapp.dto.LoginDto;
import edu.ntnu.idatt2105.quizapp.dto.RegistrationDto;
import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.repositories.RoleRepository;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

  private final JwtService jwtService;

  //Authenticates login requests.
  private final AuthenticationManager authenticationManager;

  //Password encoder to hash passwords in database
  private final BCryptPasswordEncoder passwordEncoder;

  /**
   * Register method which contains logic to create a user from the given request.
   *
   * @param registrationDto is a data transfer object which encapsulates information regarding
   *                        registering a user.
   */
  public void registerUser(RegistrationDto registrationDto) {

    String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());
    Role userRole = roleRepository.findByAuthority("USER").get();

    Set<Role> authorities = new HashSet<>();

    authorities.add(userRole);

    User user = User.builder()
            .username(registrationDto.getUsername())
            .password(encodedPassword)
            .email(registrationDto.getEmail())
            .name(registrationDto.getName())
            .surName(registrationDto.getSurname())
            .authorities(authorities)
            .build();

    userRepository.save(user);
  }

  /**
   * Login method which contains logic to authenticate a login request.
   *
   * @param loginDto is a data transfer object which encapsulates login credentials.
   * @return if authentication is successful. It retrieves and returns the user from the database.
   */
  public AuthenticationDto loginUser(LoginDto loginDto) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
    );

    User user = userRepository.findUserByUsername(loginDto.getUsername()).get();

    return new AuthenticationDto(jwtService.generateToken(user));
  }
}
