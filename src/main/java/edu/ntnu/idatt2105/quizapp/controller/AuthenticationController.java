package edu.ntnu.idatt2105.quizapp.controller;

import edu.ntnu.idatt2105.quizapp.dto.AuthenticationDto;
import edu.ntnu.idatt2105.quizapp.services.AuthenticationService;
import edu.ntnu.idatt2105.quizapp.dto.LoginDto;
import edu.ntnu.idatt2105.quizapp.dto.RegistrationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling user authentication operations
 * such as registration and login.
 *
 * @author Ramtin Samavat
 * @author Jeffrey Tabiri
 * @version 1.0
 * @since 2024-03-22
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * REST-endpoint to register a user.
   *
   * @param registrationDto DTO containing user registration information.
   * @return ResponseEntity containing a DTO with a token on success, or error on failure.
   */
  @PostMapping("/register")
  public ResponseEntity<AuthenticationDto> registerUser(@RequestBody RegistrationDto registrationDto) {
    log.info("Attempting to register user: {}", registrationDto.getUsername());
    try {
      AuthenticationDto authenticationDto = authenticationService.registerUser(registrationDto);
      log.info("User {} registered successfully.", registrationDto.getUsername());
      return new ResponseEntity<>(authenticationDto, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      log.info("Failed to register user {}: {}", registrationDto.getUsername(), e.getMessage());
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    } catch (Exception e) {
      log.error("Failed to register user {}: {}", registrationDto.getUsername(), e.getMessage(), e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * REST-endpoint to authenticate a user login request.
   *
   * @param loginDto DTO containing user login credentials.
   * @return ResponseEntity containing a DTO with a token on success, or UNAUTHORIZED on failure.
   */
  @PostMapping("/login")
  public ResponseEntity<AuthenticationDto> loginUser(@RequestBody LoginDto loginDto) {
    log.info("User {} has attempted to log in.", loginDto.getUsername());
    try {
      AuthenticationDto authenticationDto = authenticationService.authenticateUser(loginDto);
      log.info("User {} successfully logged in.", loginDto.getUsername());
      return new ResponseEntity<>(authenticationDto, HttpStatus.OK);
    } catch (Exception e) {
      log.error("User {} failed to log in: {}", loginDto.getUsername(), e.getMessage());
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }
}
