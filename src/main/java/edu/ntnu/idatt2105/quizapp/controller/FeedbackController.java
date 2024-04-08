package edu.ntnu.idatt2105.quizapp.controller;

import edu.ntnu.idatt2105.quizapp.dto.FeedbackDto;
import edu.ntnu.idatt2105.quizapp.services.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling user feedback-related endpoints.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

  private final FeedbackService feedbackService;

  /**
   * REST-endpoint for submitting user feedbacks.
   *
   * @param feedbackDto The feedback DTO containing feedback information.
   * @return ResponseEntity indicating the status of the feedback submission.
   */

  @Operation(summary = "Submit user feedbacks")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Feedback successfully submitted."),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PostMapping(path = "/submit")
  public ResponseEntity<Void> submitFeedback(@RequestBody FeedbackDto feedbackDto) {
    log.info("Feedback received from {}.", feedbackDto.getEmail());
    feedbackService.saveFeedBackMessage(feedbackDto);
    log.info("Feedback from {} submitted successfully.", feedbackDto.getEmail());
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
