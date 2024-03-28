package edu.ntnu.idatt2105.quizapp.services;

import edu.ntnu.idatt2105.quizapp.dto.FeedbackDto;
import edu.ntnu.idatt2105.quizapp.model.FeedbackMessage;
import edu.ntnu.idatt2105.quizapp.repositories.FeedbackRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Service class that encapsulates the logic for handling feedback-related operations.
 * It uses the {@link FeedbackRepository} to perform feedback-related operations in the database.
 *
 * @author Ramtin Samavat
 * @version  1.0
 */
@Service
@RequiredArgsConstructor
public class FeedbackService {

  private final FeedbackRepository feedbackRepository;

  /**
   * The method saves a feedback message to the database.
   *
   * @param feedbackDto The feedback DTO containing feedback information.
   * @throws NullPointerException If the feedbackDto object is null.
   */
  public void saveFeedBackMessage(@NonNull FeedbackDto feedbackDto) throws NullPointerException {

    FeedbackMessage feedbackMessage = FeedbackMessage.builder()
            .email(feedbackDto.getEmail())
            .name(feedbackDto.getName())
            .surname(feedbackDto.getSurname())
            .content(feedbackDto.getContent())
            .timestamp(LocalDateTime.now())
            .build();

    feedbackRepository.save(feedbackMessage);
  }
}
