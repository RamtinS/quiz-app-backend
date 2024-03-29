package edu.ntnu.idatt2105.quizapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.idatt2105.quizapp.dto.PublicUserInformationDTO;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing users
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@RequestMapping("/api/v1")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class UserController {
  private UserService searchService;

  /**
   * Finds a list of users based on the search parameter.
   *
   * @param search the search parameter.
   * @param page   the page number.
   * @param size   the size of the page.
   * @return a list of users based on the search parameter.
   */
  @Operation(summary = "Search for users by username")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              content = {
                  @Content(
                      mediaType = "application/json",
                      array = @ArraySchema(schema = @Schema(implementation = User.class)))
              }),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error"
          )
      })
  @GetMapping("/users")
  public ResponseEntity<List<PublicUserInformationDTO>> searchUsername(
      @RequestParam String search,
      @RequestParam int page,
      @RequestParam int size
  ) {
    log.info("Searching for users with username: " + search);

    try {
      List<PublicUserInformationDTO> result = searchService.findPublicProfilesFromUsername(search,
          Pageable.ofSize(size).withPage(page));

      ObjectMapper objectMapper = new ObjectMapper();
      String json = objectMapper.writeValueAsString(result);

      log.info("Found users: " + json);

      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }


}
