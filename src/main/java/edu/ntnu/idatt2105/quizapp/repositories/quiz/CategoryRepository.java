package edu.ntnu.idatt2105.quizapp.repositories.quiz;

import edu.ntnu.idatt2105.quizapp.model.quiz.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for CRUD-queries with a category class.
 * @author Jeffrey Yaw Annor Tabiri
 * @since 3/31/2024
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
