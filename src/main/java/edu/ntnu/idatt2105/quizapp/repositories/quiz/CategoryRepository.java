package edu.ntnu.idatt2105.quizapp.repositories.quiz;

import edu.ntnu.idatt2105.quizapp.model.quiz.Category;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for CRUD-queries with a category class.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @since 3/31/2024
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findCategoriesByDescription(String description);

  Optional<Category> findCategoryByDescription(String description);

}
