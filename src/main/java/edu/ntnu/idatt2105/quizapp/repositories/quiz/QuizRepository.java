package edu.ntnu.idatt2105.quizapp.repositories.quiz;

import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the Quiz entity
 *
 * @author Tobias Oftedal
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
  List<Quiz> findAllByAuthorUsername(String username, Pageable pageable);

  List<Quiz> findAllByAuthorUsernameAndIsOpen(String username, Pageable pageable, boolean open);

  List<Quiz> findAllByIsOpen(boolean open, Pageable pageable);

  Optional<Quiz> findQuizById(Long id);

  List<Quiz> findAllByNameContainingIgnoreCaseAndIsOpenOrderByName(String title, Boolean isOpen, Pageable pageable);

  List<Quiz> findQuizByTagsDescriptionContainingIgnoreCaseAndIsOpen(String description, Boolean isOpen, Pageable pageable);


  List<Quiz> findQuizByCategoryDescriptionContainingIgnoreCaseAndIsOpen(String description, Boolean isOpen, Pageable pageable);

  List<Quiz> findQuizByCategoryDescriptionAndTagsDescriptionContainingIgnoreCaseAndIsOpen(String categoryDescription, String tagDescription, Boolean isOpen, Pageable pageable);
}
