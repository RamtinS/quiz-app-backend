package edu.ntnu.idatt2105.quizapp.controller;


import edu.ntnu.idatt2105.quizapp.dto.AuthenticationDto;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.services.AuthenticationService;
import edu.ntnu.idatt2105.quizapp.dto.LoginDto;
import edu.ntnu.idatt2105.quizapp.dto.RegistrationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  Controller class responsible for handling user authentication operations
 *  such as registration and login.
 *  Exposes REST endpoints for user registration and login.
 *
 * @version 1.0
 * @since 2024-03-22
 * @author Jytabiri
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * REST-endpoint to register a user.
   *
   * @param body is a data transfer object which encapsulates
   *             information regarding a register request
   * @return //TODO should return a status OK
   */
  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody RegistrationDto body) {
    log.info("Registering user: {}", body.getUsername());

    authenticationService.registerUser(body);

    return new ResponseEntity<>("User successfully registered account.", HttpStatus.OK);
  }

  /**
   * REST-endpoint to log in a user.
   *
   * @param body is a data transfer object which encapsulates information regarding a login request
   * @return //TODO should return an approriate DTO to client, WITH a JWT token and a status OK.
   */
  @PostMapping("/login")
  public ResponseEntity<AuthenticationDto> loginUser(@RequestBody LoginDto body) {
    log.info("Logging in user: {}", body.getUsername());

    return new ResponseEntity<>(authenticationService.loginUser(body), HttpStatus.OK);
  }

}
