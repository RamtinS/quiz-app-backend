package edu.ntnu.idatt2105.quizapp.model.quiz;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The MultipleChoiceQuestion class represents a multiple choice question entity in the application.
 * Each MultipleChoiceQuestion object encapsulates information about a multiple choice question.
 * This class extends the Question class.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "multiple_choice_questions")
public class MultipleChoiceQuestion extends Question {


  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  private List<Answer> answers;



  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    MultipleChoiceQuestion that = (MultipleChoiceQuestion) object;
    return super.equals(object)
            && Objects.equals(answers, that.answers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(answers);
  }
}
