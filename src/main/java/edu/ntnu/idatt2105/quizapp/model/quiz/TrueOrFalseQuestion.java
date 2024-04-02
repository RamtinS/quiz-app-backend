package edu.ntnu.idatt2105.quizapp.model.quiz;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "true_or_false_questions")
public class TrueOrFalseQuestion extends Question {

  @Column(nullable = false, name = "question_is_correct")
  private Boolean questionIsCorrect;
}
