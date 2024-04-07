package edu.ntnu.idatt2105.quizapp.services.quiz;

import edu.ntnu.idatt2105.quizapp.model.quiz.Category;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Tag entities.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  /**
   * Retrieves all possible categories stored in the database.
   *
   * @param pageable Pageable object.
   * @return a list of all possible categories
   */
  public Page<Category> getAllPossibleCategories(Pageable pageable) {
    return categoryRepository.findAll(pageable);
  }
}
