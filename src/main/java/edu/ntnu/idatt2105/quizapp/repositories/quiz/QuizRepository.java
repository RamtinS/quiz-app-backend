package edu.ntnu.idatt2105.quizapp.repositories.quiz;

import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the Quiz entity
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
  List<Quiz> findAllByAuthorUsername(String username, Pageable pageable);

  List<Quiz> findAllByAuthorUsernameAndIsOpen(String username, Pageable pageable, boolean open);

  List<Quiz> findAllByIsOpen(boolean open, Pageable pageable);

  Optional<Quiz> findQuizById(Long id);

  List<Quiz> findAllByNameContainingIgnoreCaseOrderByName(String title, Pageable pageable);


}
