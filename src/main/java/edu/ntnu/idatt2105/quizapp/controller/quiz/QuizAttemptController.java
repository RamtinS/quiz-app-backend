package edu.ntnu.idatt2105.quizapp.controller.quiz;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizAttemptDto;
import edu.ntnu.idatt2105.quizapp.services.quiz.QuizAttemptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling quiz attempt-related endpoints.
 *
 * @author Ramitn Samavat
 * @version 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz-attempt")
public class QuizAttemptController {

  private final QuizAttemptService quizAttemptService;

  /**
   * REST-endpoint for submitting quiz attempts.
   *
   * @param quizAttemptDto The DTO containing quiz attempt information.
   * @param principal The authenticated principal.
   * @return ResponseEntity indicating the status of the quiz attempt submission.
   */
  @Operation(summary = "Submit quiz attempt")
  @ApiResponse(responseCode = "200", description = "Attempts successfully submitted.")
  @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  @PostMapping()
  public ResponseEntity<Void> submitAttempt(@RequestBody QuizAttemptDto quizAttemptDto,
                                            Principal principal) {
    String username = principal.getName();
    log.info("Quiz attempt received from {}.", username);
    quizAttemptService.saveQuizAttempt(quizAttemptDto, username);
    log.info("Quiz attempt from {} submitted successfully.", username);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
