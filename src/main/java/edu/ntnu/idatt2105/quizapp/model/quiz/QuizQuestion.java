package edu.ntnu.idatt2105.quizapp.model.quiz;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class for QuizQuestion.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "questions")
public class QuizQuestion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "question_text")
  private String questionText;

  @OneToMany(mappedBy = "quizQuestion", cascade = CascadeType.ALL)
  private List<Answer> answers;

  @ManyToOne(fetch = FetchType.LAZY)
  private Quiz quiz;

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    QuizQuestion that = (QuizQuestion) object;
    return Objects.equals(id, that.id) &&
        Objects.equals(questionText, that.questionText) &&
        Objects.equals(answers, that.answers) && Objects.equals(quiz, that.quiz);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionText, quiz);
  }
}
