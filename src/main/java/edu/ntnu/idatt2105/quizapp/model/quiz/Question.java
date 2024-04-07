package edu.ntnu.idatt2105.quizapp.model.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;


/**
 * The Question class represents a question entity in the application.
 * Each Question object encapsulates information about a question.
 * This class is abstract and should be extended by other question types.
 *
 * @author Tobias Oftedal
 * @see MultipleChoiceQuestion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "questions")
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private String questionText;

  @ManyToOne
  private Quiz quiz;




}
