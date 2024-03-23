package edu.ntnu.idatt2105.quizapp.controller;

import edu.ntnu.warehouseapp.dto.user.LoginDto;
import edu.ntnu.warehouseapp.dto.user.RegistrationDto;
import edu.ntnu.warehouseapp.model.User;
import edu.ntnu.warehouseapp.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public User registerUser(@RequestBody RegistrationDto body) {
    log.info("Registering user: {}", body.getUsername());

    return authenticationService.registerUser(body.getUsername(), body.getPassword());
  }

  /**
   * REST-endpoint to log in a user.
   *
   * @param body is a data transfer object which encapsulates information regarding a login request
   * @return //TODO should return an approriate DTO to client, WITH a JWT token and a status OK.
   */
  @PostMapping("/login")
  public User loginUser(@RequestBody LoginDto body) {
    log.info("Logging in user: {}", body.getUsername());

    return authenticationService.loginUser(body.getUsername(), body.getPassword());
  }

}
