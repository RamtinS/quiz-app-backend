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

  List<Quiz> findAllByNameContainingIgnoreCaseOrderByName(String title, Pageable pageable);

  List<Quiz> findQuizByTagsDescription(String description, Pageable pageable);

  List<Quiz> findQuizByTagsId(Long id);

  List<Quiz> findQuizByCategoryDescription(String description);

  List<Quiz> findQuizByCategoryId(Long id);
}
