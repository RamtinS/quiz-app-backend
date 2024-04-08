package edu.ntnu.idatt2105.quizapp.model.quiz;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * The Category class represents a category entity in the application.
 * Each Category object encapsulates information about a category.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Entity
@Table(name = "categories")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Long id;

  @Column(name = "description", nullable = false, unique = true)
  @NonNull
  private String description;

  @OneToMany(mappedBy = "category")
  private List<Quiz> quizzes;


  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Category category = (Category) object;
    return Objects.equals(id, category.id)
            && Objects.equals(description, category.description)
            && Objects.equals(quizzes, category.quizzes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description);
  }

}
