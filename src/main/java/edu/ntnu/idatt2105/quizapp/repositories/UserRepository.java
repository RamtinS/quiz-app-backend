package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for managing User entities.
 * The interface extends JpaRepository.
 * The class allows for basic CRUD (Create, Read, Update, Delete) operations on users.
 *
 * @author Jeffrey Tabiri
 * @version 1.0
 * @since 2024-03-22
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserByUsername(String username);
}
