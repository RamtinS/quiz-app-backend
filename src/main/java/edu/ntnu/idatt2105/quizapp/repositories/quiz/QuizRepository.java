package edu.ntnu.idatt2105.quizapp.repositories.quiz;

import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Quiz entities.
 * The interface extends JpaRepository, allowing basic CRUD
 * (Create, Read, Update, Delete) operations on users.
 *
 * @author Tobias Oftedal
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

  /**
   * Retrieves all quizzes created by a user with the given username.
   *
   * @param username The username of the author.
   * @param pageable The pageable information for pagination.
   * @return A list of quizzes created by the specified user.
   */
  List<Quiz> findAllByAuthorUsername(String username, Pageable pageable);

  /**
   * Retrieves all quizzes created by a user with the given username and specified openness.
   *
   * @param username The username of the author.
   * @param pageable The pageable information for pagination.
   * @param open The openness status of the quizzes to filter by.
   * @return A list of quizzes created by the specified user with the specified openness status.
   */
  List<Quiz> findAllByAuthorUsernameAndIsOpen(String username, Pageable pageable, boolean open);

  /**
   * Retrieves all quizzes with the specified openness status.
   *
   * @param open The openness status of quizzes to retrieve.
   * @param pageable The pageable information for pagination.
   * @return A list containing quizzes matching the openness status.
   */
  List<Quiz> findAllByIsOpen(boolean open, Pageable pageable);

  /**
   * Retrieves a quiz by its ID.
   *
   * @param id The ID of the quiz.
   * @return An Optional containing the quiz, if found.
   */
  Optional<Quiz> findQuizById(Long id);

  /**
   * Retrieves quizzes with names containing the given title, openness status,
   * and orders them by name.
   *
   * @param title The title to search for.
   * @param isOpen The openness status of the quizzes to filter by.
   * @param pageable The pageable information for pagination.
   * @return A list of quizzes matching the specified criteria.
   */
  List<Quiz> findAllByNameContainingIgnoreCaseAndIsOpenOrderByName(
          String title, Boolean isOpen, Pageable pageable);

  /**
   * Retrieves quizzes by tags containing the specified description and openness status.
   *
   * @param description The description of the tag.
   * @param isOpen The openness status of quizzes to retrieve.
   * @param pageable The pageable information for pagination.
   * @return A list containing quizzes matching the criteria.
   */
  List<Quiz> findQuizByTagsDescriptionContainingIgnoreCaseAndIsOpen(
          String description, Boolean isOpen, Pageable pageable);

  /**
   * Retrieves quizzes by category containing the specified description and openness status.
   *
   * @param description The description of the category.
   * @param isOpen The openness status of quizzes to retrieve.
   * @param pageable The pageable information for pagination.
   * @return A list containing quizzes matching the criteria.
   */
  List<Quiz> findQuizByCategoryDescriptionContainingIgnoreCaseAndIsOpen(
          String description, Boolean isOpen, Pageable pageable);

  /**
   * Retrieves quizzes by category and tags containing the specified descriptions
   * and openness status.
   *
   * @param categoryDescription The description of the category.
   * @param tagDescription The description of the tag.
   * @param isOpen The openness status of quizzes to retrieve.
   * @param pageable The pageable information for pagination.
   * @return A list containing quizzes matching the criteria.
   */
  List<Quiz> findQuizByCategoryDescriptionAndTagsDescriptionContainingIgnoreCaseAndIsOpen(
          String categoryDescription, String tagDescription, Boolean isOpen, Pageable pageable);
}
