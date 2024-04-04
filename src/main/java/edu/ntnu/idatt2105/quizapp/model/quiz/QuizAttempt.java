package edu.ntnu.idatt2105.quizapp.model.quiz;

import edu.ntnu.idatt2105.quizapp.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Entity model for quiz attempts.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 * @since 02/04/2024
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuizAttempt {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "quiz_id", nullable = false)
  @NonNull
  private Quiz quiz;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @NonNull
  private User user;

  @Column(name = "score", nullable = false)
  private int score;

  @Column(name = "attempt_timestamp", nullable = false)
  @NonNull
  private Date attemptDate;
}
