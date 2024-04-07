package edu.ntnu.idatt2105.quizapp.model.quiz;

import edu.ntnu.idatt2105.quizapp.model.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
 * The Quiz class represents a quiz entity in the application.
 * Each Quiz object encapsulates information about a quiz.
 * This class is an entity class in the JPA (Java Persistence API) and is used to
 * map the Quiz objects to the quizzes table in the database.
 *
 * @author Tobias Oftedal
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
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

  @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
  private List<Question> questions;

  @ManyToOne(fetch = FetchType.LAZY)
  private User author;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "quiz_tags",
          joinColumns = {@JoinColumn(name = "quiz_id")},
          inverseJoinColumns = {@JoinColumn(name = "tag_id")})
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
    return Objects.equals(id, quiz.id) && Objects.equals(name, quiz.name)
            && Objects.equals(description, quiz.description)
            && Objects.equals(questions, quiz.questions)
            && Objects.equals(author, quiz.author)
            && Objects.equals(category, quiz.category)
            && Objects.equals(isOpen, quiz.isOpen);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, category, isOpen);
  }
}
