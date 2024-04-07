package edu.ntnu.idatt2105.quizapp.controller.quiz;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizPreviewDTO;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationRequestDTO;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationResponseDTO;
import edu.ntnu.idatt2105.quizapp.services.quiz.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
      @ApiResponse(responseCode = "200", description = "The quiz was created and saved " +
          "successfully", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation =
              QuizCreationResponseDTO.class))}),
      @ApiResponse(responseCode = "500", description = "Unknown internal server error", content =
      @Content)})
  @PostMapping("/quizzes")
  public ResponseEntity<QuizCreationResponseDTO> submitQuiz(
      @RequestBody QuizCreationRequestDTO quizCreationRequestDTO,
      Principal authenticatedPrincipal) {


    log.info("Submit quiz for user: " + authenticatedPrincipal.getName());

    QuizCreationResponseDTO quizCreationResponseDTO =
        quizService.createQuiz(quizCreationRequestDTO, authenticatedPrincipal);

    log.info("Quiz has been saved");

    return new ResponseEntity<>(quizCreationResponseDTO, HttpStatus.OK);

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
  @Operation(summary = "Get all quizzes for a specified user paginated", description =
      "If the user is not the same as the authenticated user, only " +
          "public quizzes are returned")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "The quizzes were found and returned"),
      @ApiResponse(responseCode = "500", description = "Unknown internal server error", content =
      @Content)})
  @Parameters({
      @Parameter(name = "pageSize", description = "The size of the requested page"),
      @Parameter(name = "page", description = "The page number of the requested page"),
      @Parameter(name = "username", description = "The username of the user who owns the quizzes")})
  @GetMapping("/users/{username}/previews")
  public ResponseEntity<List<QuizPreviewDTO>> getPreviewsForUserPaginated(
      Principal principal, @RequestParam int page, @RequestParam int pageSize,
      @PathVariable String username) {

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
      @ApiResponse(responseCode = "200", description = "The public quizzes were found and " +
          "returned", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation =
              QuizPreviewDTO.class))}),
      @ApiResponse(responseCode = "500", description =
          "The public quizzes could not be found" +
              " due to an internal server error", content = @Content)})
  @GetMapping("browser/previews")
  public ResponseEntity<List<QuizPreviewDTO>> getAllPublicQuizPreviewsPaginated(
      @RequestParam int pageSize, @RequestParam int page) {
    log.info("Browsing public quizzes");


    PageRequest pageRequest = PageRequest.of(page, pageSize);

    List<QuizPreviewDTO> quizzes = quizService.browsePublicQuizzesPaginated(pageRequest);

    log.info("Returning " + quizzes.size() + " quizzes");
    return new ResponseEntity<>(quizzes, HttpStatus.OK);
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
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation =
                  QuizDto.class))}),
      @ApiResponse(responseCode = "400", description = "The quiz could not be found", content =
      @Content),
      @ApiResponse(responseCode = "403", description = "The user is not authorized to view the " +
          "quiz", content = @Content),
      @ApiResponse(responseCode = "500", description = "Unknown internal server error", content =
      @Content),})
  @GetMapping("quizzes/{quizId}")
  public ResponseEntity<QuizDto> getQuizById(Principal principal,
                                             @PathVariable long quizId) {

    log.info("Returning quiz: " + quizId);
    try {
      log.info("Get quiz by id: " + quizId);
      QuizDto quiz = quizService.getQuizById(principal, quizId);
      log.info("Returning quiz: " + quiz.getName());
      return new ResponseEntity<>(quiz, HttpStatus.OK);

    } catch (IllegalArgumentException | NullPointerException e) {
      log.error("Error: of type " + e.getClass().getName(), e);
      return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

    } catch (NoSuchElementException e) {
      log.error("Error: of type " + e.getClass().getName(), e);
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    } catch (Exception e) {
      log.error("Error: of type " + e.getClass().getName(), e);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Get all public quizzes with the defined criteria paginated.
   *
   * @param pageSize the size of the requested page
   * @param page     the page number of the requested page
   * @return ResponseEntity containing a list of quiz previews, or a null response with
   * a {@link HttpStatus#BAD_REQUEST}.
   */
  @Operation(summary = "Get all public with certain criteria quizzes paginated")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "The public quizzes with the specified " +
          "criteria were found and returned", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation =
              QuizPreviewDTO.class))}),
      @ApiResponse(responseCode = "500", description = "The quizzes could not be found" +
          " due to an internal server error", content = @Content)})
  @Parameters({
      @Parameter(name = "pageSize", description = "The size of the requested page"),
      @Parameter(name = "page", description = "The page number of the requested page"),
      @Parameter(name = "title", description = "The title of the quiz"),
      @Parameter(name = "searchInCategory", description = "Whether to search in the categories"),
      @Parameter(name = "searchInTags", description = "Whether to search in the tags")})
  @GetMapping("browser/search")
  public ResponseEntity<List<QuizPreviewDTO>> getAllQuizzesWithDefinedCriteria(
      @RequestParam int pageSize, @RequestParam int page, @RequestParam String title,
      @RequestParam boolean searchInCategory, @RequestParam boolean searchInTags) {

    log.info("Browsing quizzes with criteria");

    if (searchInCategory) {
      log.info("Criteria: Category");
    }

    if (searchInTags) {
      log.info("Criteria: Tags");
    }

    try {

      PageRequest pageRequest = PageRequest.of(page, pageSize);

      List<QuizPreviewDTO> quizzes =
          quizService.getQuizBySearchParameters(title, searchInCategory, searchInTags,
              pageRequest);

      log.info("Returning " + quizzes.size() + " quizzes");
      return new ResponseEntity<>(quizzes, HttpStatus.OK);

    } catch (Exception e) {
      log.error("Error: of type " + e.getClass().getName(), e);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  /**
   * Update a quiz by its id.
   *
   * @param principal              the authenticated principal
   * @param quizId                 the id of the quiz to be updated
   * @param quizCreationRequestDTO the DTO containing the updated quiz
   * @return ResponseEntity containing the updated quiz, or a null response with a status code if
   * something goes wrong. If the quiz cannot be found, a {@link HttpStatus#BAD_REQUEST} status is
   * returned. If the user is not authorized to update the quiz, a {@link HttpStatus#FORBIDDEN}
   * status
   * is. If something else goes wrong, a {@link HttpStatus#INTERNAL_SERVER_ERROR} status is
   * returned.
   */
  @Operation(summary = "Update a quiz by its id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "The quiz was updated and returned",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation =
                  QuizCreationResponseDTO.class))}),
      @ApiResponse(responseCode = "404", description = "The quiz could not be found", content =
      @Content),
      @ApiResponse(responseCode = "403", description = "The user is not authorized to update the " +
          "quiz", content = @Content),
      @ApiResponse(responseCode = "500", description = "Unknown internal server error", content =
      @Content),})
  @PutMapping("quizzes/{quizId}")
  public ResponseEntity<QuizCreationResponseDTO> updateQuiz(Principal principal,
                                                            @PathVariable long quizId,
                                                            @RequestBody
                                                            QuizCreationRequestDTO quizCreationRequestDTO) {
    log.info("Update quiz by id: " + quizId);
    QuizCreationResponseDTO quizCreationResponseDTO =
        quizService.updateQuiz(principal, quizId, quizCreationRequestDTO);
    log.info("Returning quiz: " + quizCreationResponseDTO.getQuizId());
    return new ResponseEntity<>(quizCreationResponseDTO, HttpStatus.OK);
  }

  /**
   * Delete a quiz by its id.
   *
   * @param principal the authenticated principal
   * @param quizId    the id of the quiz to be deleted
   * @return ResponseEntity containing a message indicating the status of the deletion, or a null
   * response with a status code if something goes wrong. If the quiz cannot be found, a {@link
   * HttpStatus#BAD_REQUEST} status is returned. If the user is not authorized to delete the quiz, a
   * {@link HttpStatus#FORBIDDEN} status is. If something else goes wrong, a {@link
   * HttpStatus#INTERNAL_SERVER_ERROR} status is returned.
   */
  @Operation(summary = "Delete a quiz by its id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "The quiz was deleted", content = {
          @Content(mediaType = "application/json", schema =
          @Schema(implementation = String.class))}),
      @ApiResponse(responseCode = "404", description = "The quiz could not be found", content =
      @Content),
      @ApiResponse(responseCode = "403", description = "The user is not authorized to delete the " +
          "quiz", content = @Content),
      @ApiResponse(responseCode = "409", description = "The quiz could not be deleted", content =
      @Content),
      @ApiResponse(responseCode = "500", description = "Unknown internal server error", content =
      @Content)})
  @DeleteMapping("quizzes/{quizId}")
  public ResponseEntity<String> deleteQuiz(Principal principal,
                                           @PathVariable long quizId) {
    log.info("Trying to delete quiz: " + quizId);
    if (quizService.deleteQuiz(principal, quizId)) {
      return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Could not delete quiz", HttpStatus.CONFLICT);
    }
  }

}