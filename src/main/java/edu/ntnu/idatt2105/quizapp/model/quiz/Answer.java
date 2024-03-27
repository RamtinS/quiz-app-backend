package edu.ntnu.idatt2105.quizapp.model.quiz;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class for Answer.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answers")
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String answerText;
  private boolean isCorrect;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private QuizQuestion quizQuestion;


}


