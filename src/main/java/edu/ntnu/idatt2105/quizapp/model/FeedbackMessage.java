package edu.ntnu.idatt2105.quizapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The FeedbackMessage class represents a message entity in the application.
 * Each FeedbackMessage object encapsulates information about a user's feedback.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
@Entity
@Table(name = "feedback_messages")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "message_id")
  private Long id;

  @Column(name = "email")
  @NonNull
  private String email;

  @Column(name = "first_name")
  private String name;

  @Column(name = "last_name")
  private String surname;

  @Column(name = "content")
  @NonNull
  private String content;

  @Column(name = "timestamp")
  @NonNull
  private LocalDate timestamp;
}
