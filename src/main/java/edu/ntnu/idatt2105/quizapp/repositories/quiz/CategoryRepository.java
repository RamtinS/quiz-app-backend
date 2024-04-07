package edu.ntnu.idatt2105.quizapp.repositories.quiz;

import edu.ntnu.idatt2105.quizapp.model.quiz.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing category entities.
 * The interface extends JpaRepository, allowing basic CRUD
 * (Create, Read, Update, Delete) operations on users.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

  /**
   * Retrieves a category by its description.
   *
   * @param description the description of the category to search for.
   * @return an Optional containing the category, if found.
   */
  Optional<Category> findCategoryByDescription(String description);

}
