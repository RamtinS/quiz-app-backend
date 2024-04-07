package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.FeedbackDto;
import edu.ntnu.idatt2105.quizapp.model.FeedbackMessage;
import java.time.LocalDate;
import lombok.NonNull;
import org.springframework.stereotype.Component;

/**
 * Mapper class responsible for mapping FeedbackDto to FeedbackMessage.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
@Component
public class FeedbackMapper {

  /**
   * Maps a FeedbackDto object to a FeedbackMessage object.
   *
   * @param feedbackDto The feedback DTO containing feedback information.
   * @return FeedbackMessage object mapped from the provided FeedbackDto.
   */
  public FeedbackMessage mapToFeedbackMessage(@NonNull FeedbackDto feedbackDto) {
    return FeedbackMessage.builder()
            .email(feedbackDto.getEmail())
            .name(feedbackDto.getName())
            .surname(feedbackDto.getSurname())
            .content(feedbackDto.getContent())
            .timestamp(LocalDate.now())
            .build();
  }
}
