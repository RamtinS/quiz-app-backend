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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class that encapsulates the logic for registering new users
 * and authenticating existing users within the database.
 * It uses repositories to perform user-related operations in the database,
 * and uses spring boot security for user authentication.
 *
 * @author Jeffrey Tabiri, Ramtin Samavat
 * @version 1.0
 * @since 2024-03-25
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

  //CRUD operations on user models.
  private final UserRepository userRepository;

  //CRUD operations on role models.
  private final RoleRepository roleRepository;

  //Provides services related to JWT.
  private final JwtService jwtService;

  //Authenticates login requests.
  private final AuthenticationManager authManager;

  //Password encoder to hash passwords in a database.
  private final BCryptPasswordEncoder passwordEncoder;

  /**
   * Registers a new user based on the provided registration information from the DTO.
   *
   * @param registrationDto DTO containing user registration information.
   * @return An AuthenticationDto containing a token if registration is successful.
   */
  public AuthenticationDto registerUser(RegistrationDto registrationDto) {

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

    String token = jwtService.generateToken(user);

    return new AuthenticationDto(token);
  }

  /**
   * Login method which contains logic to authenticate a login request.
   *
   * @param loginDto DTO containing user login credentials.
   * @return An AuthenticationDto containing a token if authentication is successful.
   */
  public AuthenticationDto authenticateUser(@NonNull LoginDto loginDto) {
    authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

    User user = userRepository.findUserByUsername(loginDto.getUsername()).get();

    String token = jwtService.generateToken(user);

    return new AuthenticationDto(token);
  }
}
