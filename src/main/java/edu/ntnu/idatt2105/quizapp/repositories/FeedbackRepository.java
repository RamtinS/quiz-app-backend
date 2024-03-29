package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.model.FeedbackMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing feedback message entities.
 * The interface extends JpaRepository.
 * The class allows for basic CRUD (Create, Read, Update, Delete) operations.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackMessage, Long> {
}
