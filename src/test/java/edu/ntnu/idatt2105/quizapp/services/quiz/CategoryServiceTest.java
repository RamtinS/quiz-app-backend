
package edu.ntnu.idatt2105.quizapp.services.quiz;

import edu.ntnu.idatt2105.quizapp.model.quiz.Category;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the CategoryService class. The tests are done with Mockito.
 * The tests are testing the functionality of the methods in the CategoryService class.
 *
 * @version 1.0
 * @author Jeffrey Yaw Annor Tabiri
 */
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

  @InjectMocks
  CategoryService categoryService;

  @Mock
  CategoryRepository categoryRepository;

  Pageable pageable;

  @BeforeEach
  void setUp() {
    pageable = Pageable.unpaged();
  }

  @Test
  void CategoryService_GetAllPossibleCategories_ReturnCategory() {
    // Arrange
    when(categoryRepository.findAll(any(Pageable.class))).thenReturn(
            new PageImpl<>(List.of(
                    Category.builder().description("Sports").build())));

    // Act
    Page<Category> result = categoryService.getAllPossibleCategories(pageable);

    // Assert
    assertNotNull(result);
  }
}
