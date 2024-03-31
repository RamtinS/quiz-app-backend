package edu.ntnu.idatt2105.quizapp.controller;

import edu.ntnu.idatt2105.quizapp.dto.user.EditUserDto;
import edu.ntnu.idatt2105.quizapp.dto.user.UserDetailsDto;
import edu.ntnu.idatt2105.quizapp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling user related operations,
 * such as changing their information.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  /**
   * REST-endpoint for changing user information. .
   *
   * @param principal The authenticated principal
   * @param editUserDto The DTO containing new user information.
   * @return ResponseEntity indicating the status of the edit request.
   */
  @Operation(summary = "Change user information")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "User information successfully updated."),
          @ApiResponse(responseCode = "404", description = "User not found in the database."),
          @ApiResponse(responseCode = "409", description = "Email already in use by another user."),
          @ApiResponse(responseCode = "500", description = "Unknown internal server error.")
  })
  @PutMapping()
  public ResponseEntity<Void> editUserInformation(@RequestBody EditUserDto editUserDto,
                                                  Principal principal) {
    String username = principal.getName();
    log.info("Editing user information for user {}. {}", username, editUserDto);
    try {
      userService.editUser(username, editUserDto);
      log.info("User information for {} successfully updated.", username);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (UsernameNotFoundException e) {
      logFailedUpdate(username, e);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (IllegalArgumentException e) {
      logFailedUpdate(username, e);
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    } catch (Exception e) {
      logFailedUpdate(username, e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * REST-endpoint for changing user information.
   *
   * @param principal The authenticated principal
   * @return ResponseEntity containing user information DTO on success, or error on failure.
   */
  @Operation(summary = "Retrieve user information")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",
                  description = "User information successfully retrieved.",
                  content = {@Content(mediaType = "application/json",
                          schema = @Schema(implementation = UserDetailsDto.class))}
          ),
          @ApiResponse(responseCode = "404", description = "User not found in the database."),
          @ApiResponse(responseCode = "500", description = "Unknown internal server error")
  })
  @GetMapping(path = "/details")
  public ResponseEntity<UserDetailsDto> retrieveUserInformation(Principal principal) {
    String username = principal.getName();
    log.info("Retrieving user information for user {}.", username);
    try {
      UserDetailsDto userDetails = userService.getUserDetails(username);
      log.info("User information for {} successfully retrieved.", username);
      return new ResponseEntity<>(userDetails, HttpStatus.OK);
    } catch (UsernameNotFoundException e) {
      logFailedRetrieve(username, e);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      logFailedRetrieve(username, e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Logs a failed update operation with the provided username and exception information.
   *
   * @param username The username of the user for whom the update operation failed.
   * @param exception The exception that occurred during the update operation
   */
  private void logFailedUpdate(String username, Exception exception) {
    log.error("Failed to update user information for {}. {}", username,
            exception.getMessage(), exception);
  }

  /**
   * Logs failed user details retrieval with username and exception.
   *
   * @param username The username of the user for whom the update operation failed.
   * @param exception The exception that occurred during the update operation
   */
  private void logFailedRetrieve(String username, Exception exception) {
    log.error("Failed to retrieve user information for {}. {}", username,
            exception.getMessage(), exception);
  }
}
