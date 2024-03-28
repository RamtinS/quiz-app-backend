package edu.ntnu.idatt2105.quizapp.controller;

import edu.ntnu.idatt2105.quizapp.dto.FeedbackDto;
import edu.ntnu.idatt2105.quizapp.services.FeedbackService;
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
   * REST-endpoint for submitting feedback.
   *
   * @param feedbackDto The feedback DTO containing feedback information.
   * @return ResponseEntity indicating the status of the feedback submission.
   */
  @PostMapping(path = "/submit")
  public ResponseEntity<Void> submitFeedback(@RequestBody FeedbackDto feedbackDto) {
    log.info("Feedback received from {}.", feedbackDto.getEmail());
    try {
      feedbackService.saveFeedBackMessage(feedbackDto);
      log.info("Feedback from {} submitted successfully.", feedbackDto.getEmail());
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      log.error("Failed to summit feedback from {}.", feedbackDto.getEmail(), e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
