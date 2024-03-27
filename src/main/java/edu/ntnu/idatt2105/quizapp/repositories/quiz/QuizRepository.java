package edu.ntnu.idatt2105.quizapp.repositories.quiz;

import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the Quiz entity
 *
 * @author Tobias Oftedal
 * @version 1.0
 * @since 2024-03-27
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
  List<Quiz> findAllByAuthorUsername(String username, Pageable pageable);

  List<Quiz> findAllByAuthorUsernameAndOpen(String username, Pageable pageable, boolean open);

  List<Quiz> findAllByOpen(boolean open, Pageable pageable);

  Optional<Quiz> findQuizById(Long id);

  List<Quiz> findAllByNameContaining(String title, Pageable pageable);


}
