package edu.ntnu.idatt2105.quizapp.model.quiz;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

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
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String answerText;
  private Boolean isCorrect;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private QuizQuestion quizQuestion;

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Answer answer = (Answer) object;
    return Objects.equals(id, answer.id) &&
        Objects.equals(answerText, answer.answerText) &&
        Objects.equals(isCorrect, answer.isCorrect) &&
        Objects.equals(quizQuestion, answer.quizQuestion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, answerText, isCorrect);
  }
}


