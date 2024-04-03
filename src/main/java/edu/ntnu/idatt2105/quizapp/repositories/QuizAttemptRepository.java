package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for QuizAttempts which has CRUD-methods.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 * @since 02/04/2024
 */
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
  List<QuizAttempt> findQuizAttemptByUser_UserId (Long userId);
  List<QuizAttempt> findQuizAttemptByUser_Username (String name);
  
}
