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
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

/**
 * Entity model for quiz attempts.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
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
  @JoinColumn(name = "quiz_id")
  private Quiz quiz;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "score")
  private int score;

  @Column(name = "timestamp")
  private LocalDate timestamp;
}
