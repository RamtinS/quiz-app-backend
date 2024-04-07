
package edu.ntnu.idatt2105.quizapp.services.feedback;

import edu.ntnu.idatt2105.quizapp.dto.FeedbackDto;
import edu.ntnu.idatt2105.quizapp.mapper.FeedbackMapper;
import edu.ntnu.idatt2105.quizapp.model.FeedbackMessage;
import edu.ntnu.idatt2105.quizapp.repositories.FeedbackRepository;
import edu.ntnu.idatt2105.quizapp.services.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

  @InjectMocks
  FeedbackService feedbackService;

  @Mock
  FeedbackMapper feedbackMapper;

  @Mock
  FeedbackRepository feedbackRepository;

  FeedbackDto feedbackDto;

  @BeforeEach
  void setUp() {
    feedbackDto = new FeedbackDto("Test", "Test", "Test", "Test");
  }

  @Test
  void FeedbackService_Saves_FeedbackMessage() {
    //Act
    when(feedbackMapper.mapToFeedbackMessage(feedbackDto)).thenReturn(new FeedbackMessage());

    FeedbackMessage feedback = FeedbackMessage.builder()
            .email(feedbackDto.getEmail())
            .name(feedbackDto.getName())
            .surname(feedbackDto.getSurname())
            .content(feedbackDto.getContent())
            .timestamp(LocalDate.now())
            .build();

    when(feedbackRepository.save(Mockito.any(FeedbackMessage.class))).thenReturn(feedback);

    //Act
    assertDoesNotThrow(() -> feedbackService.saveFeedBackMessage(feedbackDto));
  }
}
