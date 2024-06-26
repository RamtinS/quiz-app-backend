package edu.ntnu.idatt2105.quizapp.repositories.quiz;

import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * Repository interface for managing QuizAttempt entities.
 * The interface extends JpaRepository, allowing basic CRUD
 * (Create, Read, Update, Delete) operations.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @author Ramtin Samavat
 * @version 1.0
 */
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

  /**
   * Retrieves a user's quiz attempts with the given username.
   *
   * @param username The username of the user to retrieve.
   * @return A list containing the user's quiz attempts.
   */
  List<QuizAttempt> findQuizAttemptByUser_Username(String username);

  /**
   * Retrieves quiz attempts for a quiz with the given ID.
   *
   * @param id The ID of the quiz.
   * @return A list containing the quiz attempts for the specified quiz.
   */
  List<QuizAttempt> findQuizAttemptByQuiz_Id(Long id);
}
