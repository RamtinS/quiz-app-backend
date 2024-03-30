package edu.ntnu.idatt2105.quizapp.model.quiz;

import edu.ntnu.idatt2105.quizapp.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

/**
 * Model class for Quiz.
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quizzes")
@Getter
@Setter
@Builder

public class Quiz {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "quiz_id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST)
  private List<QuizQuestion> questions;

  @ManyToOne(fetch = FetchType.LAZY)
  private User author;

  @ManyToOne(fetch = FetchType.LAZY)
  private Category category;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Tag> tags;

  @Column(name = "open")
  private Boolean isOpen;

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Quiz quiz = (Quiz) object;
    return Objects.equals(id, quiz.id) && Objects.equals(name, quiz.name) &&
        Objects.equals(description, quiz.description) &&
        Objects.equals(questions, quiz.questions) &&
        Objects.equals(author, quiz.author) &&
        Objects.equals(category, quiz.category) &&
        Objects.equals(isOpen, quiz.isOpen);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, category, isOpen);
  }
}
