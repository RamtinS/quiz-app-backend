package edu.ntnu.idatt2105.quizapp.controller;

import edu.ntnu.idatt2105.quizapp.dto.PublicUserInformationDto;
import edu.ntnu.idatt2105.quizapp.dto.user.EditUserDto;
import edu.ntnu.idatt2105.quizapp.dto.user.UserDetailsDto;
import edu.ntnu.idatt2105.quizapp.dto.user.UserStatsDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling user related operations,
 * such as changing their information.
 *
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
   * @param principal   The authenticated principal
   * @param editUserDto The DTO containing new user information.
   * @return ResponseEntity indicating the status of the edit request.
   */
  @Operation(summary = "Change user information")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User information successfully updated."),
      @ApiResponse(responseCode = "500", description = "Unknown internal server error.", content
          = @Content)
  })
  @PutMapping()
  public ResponseEntity<Void> editUserInformation(@RequestBody EditUserDto editUserDto,
                                                  Principal principal) {
    String username = principal.getName();
    log.info("Editing user information for user {}.", username);
    userService.editUser(username, editUserDto);
    log.info("User information for {} successfully updated.", username);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * REST-endpoint for retrieving user information.
   *
   * @param principal The authenticated principal
   * @return ResponseEntity containing user information DTO on success.
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
  @GetMapping("/public")
  public ResponseEntity<List<PublicUserInformationDto>> searchUsername(
      @RequestParam String search, @RequestParam int page, @RequestParam int size) {

    List<PublicUserInformationDto> result = searchService
        .findPublicProfilesFromUsername(search, Pageable.ofSize(size).withPage(page));

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  /**
   * REST-endpoint for retrieving user statistics.
   *
   * @param principal The authenticated principal
   * @return ResponseEntity containing a DTO with user stats on success.
   */
  @Operation(summary = "Retrieve user statistics")
  @ApiResponse(responseCode = "200", description = "User stats successfully retrieved.",
      content = {@Content(mediaType = "application/json",
          schema = @Schema(implementation = UserStatsDto.class))}
  )
  @GetMapping("/stats")
  public ResponseEntity<UserStatsDto> retrieveUserStats(Principal principal) {
    String username = principal.getName();
    log.info("Retrieving user stats for user {}.", username);
    UserStatsDto userStats = userService.getUserStats(username);
    log.info("User stats for {} successfully retrieved.", username);
    return new ResponseEntity<>(userStats, HttpStatus.OK);
  }

  /**
   * REST-endpoint for retrieving public user information for a specified user.
   *
   * @param username the username of the user to retrieve public information for.
   * @return ResponseEntity containing a DTO with public user information on success.
   */
  @Operation(summary = "Retrieve public user information for a specified user")
  @ApiResponse(responseCode = "200", description = "Public user information successfully "
      + "retrieved.",
      content = {@Content(mediaType = "application/json",
          schema = @Schema(implementation = PublicUserInformationDto.class))}
  )
  @GetMapping("/public/{username}")
  public ResponseEntity<PublicUserInformationDto> getPublicUserInfo(@PathVariable String username) {
    PublicUserInformationDto publicUserInformationDto =
        userService.getPublicUserInformation(username);
    return new ResponseEntity<>(publicUserInformationDto, HttpStatus.OK);
  }

}
