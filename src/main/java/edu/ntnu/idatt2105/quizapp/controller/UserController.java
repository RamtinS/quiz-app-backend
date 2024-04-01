package edu.ntnu.idatt2105.quizapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.idatt2105.quizapp.dto.PublicUserInformationDTO;
import edu.ntnu.idatt2105.quizapp.dto.user.EditUserDto;
import edu.ntnu.idatt2105.quizapp.dto.user.UserDetailsDto;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling user related operations,
 * such as changing their information.
 *
 * @author Ramtin Samavat
 * @author Tobias Oftedal
 * @version 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService searchService;

  private final UserService userService;

  /**
   * REST-endpoint for changing user information. .
   *
   * @param principal The authenticated principal
   * @param editUserDto The DTO containing new user information.
   * @return ResponseEntity indicating the status of the edit request.
   */
  @Operation(summary = "Change user information")
  @ApiResponse(responseCode = "200", description = "User information successfully updated.")
  @PutMapping()
  public ResponseEntity<Void> editUserInformation(@RequestBody EditUserDto editUserDto,
                                                  Principal principal) {
    String username = principal.getName();
    log.info("Editing user information for user {}. {}", username, editUserDto);
    userService.editUser(username, editUserDto);
    log.info("User information for {} successfully updated.", username);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * REST-endpoint for changing user information.
   *
   * @param principal The authenticated principal
   * @return ResponseEntity containing user information DTO on success, or error on failure.
   */
  @Operation(summary = "Retrieve user information")
  @ApiResponse(responseCode = "200", description = "User information successfully retrieved.",
          content = {@Content(mediaType = "application/json",
                  schema = @Schema(implementation = UserDetailsDto.class))}
  )
  @GetMapping(path = "/details")
  public ResponseEntity<UserDetailsDto> retrieveUserInformation(Principal principal) {
    String username = principal.getName();
    log.info("Retrieving user information for user {}.", username);
    UserDetailsDto userDetails = userService.getUserDetails(username);
    log.info("User information for {} successfully retrieved.", username);
    return new ResponseEntity<>(userDetails, HttpStatus.OK);
  }

  /**
   * Finds a list of users based on the search parameter.
   *
   * @param search the search parameter.
   * @param page   the page number.
   * @param size   the size of the page.
   * @return a list of users based on the search parameter.
   */
  @Operation(summary = "Search for users by username")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",
                  content = {@Content(mediaType = "application/json",
                          array = @ArraySchema(schema = @Schema(implementation = User.class)))}),
          @ApiResponse(responseCode = "500", description = "Internal server error")})
  @GetMapping()
  public ResponseEntity<List<PublicUserInformationDTO>> searchUsername(
          @RequestParam String search,
          @RequestParam int page,
          @RequestParam int size
  ) {
    log.info("Searching for users with username: {}", search);

    try {
      List<PublicUserInformationDTO> result = searchService.findPublicProfilesFromUsername(search,
              Pageable.ofSize(size).withPage(page));

      ObjectMapper objectMapper = new ObjectMapper();
      String json = objectMapper.writeValueAsString(result);

      log.info("Found users: {}", json);

      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (JsonProcessingException e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
