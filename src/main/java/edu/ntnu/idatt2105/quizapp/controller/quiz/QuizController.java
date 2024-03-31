package edu.ntnu.idatt2105.quizapp.controller.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizPreviewDTO;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationRequestDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationResponseDTO;
import edu.ntnu.idatt2105.quizapp.services.quiz.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling quiz related requests.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz-management")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {

  @NonNull
  private QuizService quizService;

  /**
   * Create a quiz and saves it to the database.
   *
   * @param quizCreationRequestDTO DTO containing the quiz to be created
   * @param authenticatedPrincipal the authenticated principal
   * @return ResponseEntity containing the created quiz, or an error message
   */

  @Operation(summary = "Submit a quiz for a user and save it to the database")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "The quiz was created and saved successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = QuizCreationResponseDTO.class))}),
      @ApiResponse(responseCode = "500", description = "Unknown internal server error",
          content = @Content),
  })
  @PostMapping("/quizzes")
  public ResponseEntity<QuizCreationResponseDTO> submitQuiz(
      @RequestBody @NotNull QuizCreationRequestDto quizCreationRequestDTO,
      Principal authenticatedPrincipal) {

    try {
      log.info("Submit quiz for user: " + authenticatedPrincipal.getName());

      ObjectMapper objectMapper = new ObjectMapper();
      String json = objectMapper.writeValueAsString(quizCreationRequestDTO);

      log.info("Parsing JSON from submitQuiz");
      log.info(json);

      QuizCreationResponseDTO quizCreationResponseDTO =
          quizService.createQuiz(quizCreationRequestDTO, authenticatedPrincipal);
      return new ResponseEntity<>(quizCreationResponseDTO, HttpStatus.OK);

    } catch (Exception e) {

      log.error("Error: of type " + e.getClass().getName(), e);
      QuizCreationResponseDTO errorResponse =
          QuizCreationResponseDTO.builder()
              .errorMessage("There was a problem creating the quiz")
              .build();

      return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Get all quizzes for a user paginated. If the user is not the same as the authenticated user,
   * only public quizzes are returned.
   *
   * @param principal the authenticated principal
   * @param page      the page number of the requested page
   * @param pageSize  the size of the requested page
   * @param username  the username of the user who owns the quizzes
   * @return ResponseEntity containing a list of quiz previews, or a null response with a status
   * code if something goes wrong
   */
  @Operation(summary = "Get all quizzes for a specified user paginated",
      description = "If the user is not the same as the authenticated user, only " +
          "public quizzes are returned")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "The quizzes were found and returned",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = QuizPreviewDTO.class))}),
      @ApiResponse(responseCode = "500", description = "Unknown internal server error",
          content = @Content),
  })
  @GetMapping("/users/{username}/previews")
  public ResponseEntity<List<QuizPreviewDTO>> getPreviewsForUserPaginated(Principal principal,
                                                                          @RequestParam int page,
                                                                          @RequestParam
                                                                          int pageSize,
                                                                          @PathVariable
                                                                          String username) {
    try {
      PageRequest pageRequest = PageRequest.of(page, pageSize);

      if (principal != null && principal.getName().equals(username)) {
        List<QuizPreviewDTO> quizzes =
            quizService.getAllQuizPreviewsForUserPaginated(principal, pageRequest);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
      } else {

        List<QuizPreviewDTO> quizzes =
            quizService.getAllPublicQuizPreviewsForUserPaginated(username, pageRequest);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
      }

    } catch (Exception e) {
      log.error("Error: of type " + e.getClass().getName(), e);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  /**
   * Get all public quizzes paginated.
   *
   * @param pageSize the size of the requested page
   * @param page     the page number of the requested page
   * @return ResponseEntity containing a list of quiz previews, or a null response with
   * a {@link HttpStatus#BAD_REQUEST}.
   */
  @Operation(summary = "Get all public quizzes paginated")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "The public quizzes were found and returned",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = QuizPreviewDTO.class))}),
      @ApiResponse(responseCode = "500", description = "The public quizzes could not be found" +
          " due to an internal server error",
          content = @Content)})
  @GetMapping("browser/previews")
  public ResponseEntity<List<QuizPreviewDTO>> getAllPublicQuizPreviewsPaginated(
      @RequestParam int pageSize,
      @RequestParam int page) {
    log.info("Browsing public quizzes");

    try {

      PageRequest pageRequest = PageRequest.of(page, pageSize);

      List<QuizPreviewDTO> quizzes = quizService.browsePublicQuizzesPaginated(pageRequest);

      log.info("Returning " + quizzes.size() + " quizzes");
      return new ResponseEntity<>(quizzes, HttpStatus.OK);

    } catch (Exception e) {
      log.error("Error: of type " + e.getClass().getName(), e);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Get a quiz by its id.
   *
   * @param principal the authenticated principal
   * @param quizId    the id of the quiz to be returned
   * @return ResponseEntity containing the quiz, or a null response with a status code if something
   * goes wrong. If the quiz cannot be found, a {@link HttpStatus#BAD_REQUEST} status is returned.
   * If the user is not authorized to view the quiz, a {@link HttpStatus#FORBIDDEN} status is.
   * If something else goes wrong, a {@link HttpStatus#INTERNAL_SERVER_ERROR} status is returned.
   */
    @Operation(summary = "Get a quiz by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The quiz was found and returned",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = QuizDto.class))}),
        @ApiResponse(responseCode = "400", description = "The quiz could not be found",
            content = @Content),
        @ApiResponse(responseCode = "403", description = "The user is not authorized to view the quiz",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Unknown internal server error",
            content = @Content),
    })
  @GetMapping("quizzes/{quizId}")
  public ResponseEntity<QuizDto> getQuizById(Principal principal,
                                             @PathVariable long quizId) {

    log.info("Returning quiz: " + quizId);
    try {
      log.info("Get quiz by id: " + quizId);
      QuizDto quiz = quizService.getQuizById(principal, quizId);
      return new ResponseEntity<>(quiz, HttpStatus.OK);

    } catch (IllegalArgumentException | NullPointerException e) {
      return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    } catch (Exception e) {
      log.error("Error: of type " + e.getClass().getName(), e);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}