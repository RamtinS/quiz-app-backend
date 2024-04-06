package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing User entities.
 * The interface extends JpaRepository, allowing basic CRUD
 * (Create, Read, Update, Delete) operations on users.
 *
 * @author Jeffrey Tabiri
 * @author Ramtin Samavat
 * @author Tobias Oftedal
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Retrieves user with the given username.
   *
   * @param username The username of the user to retrieve.
   * @return An Optional containing the user if found, otherwise empty.
   */
  Optional<User> findUserByUsernameIgnoreCase(String username);

  /**
   * Retrieves user with the given email.
   *
   * @param email The email of the user to retrieve
   * @return An Optional containing the user if found, otherwise empty.
   */
  Optional<User> findUserByEmailIgnoreCase(String email);


  /**
   * Retrieves all users with usernames containing the given username.
   */
  List<User> findAllByUsernameContainingIgnoreCase(String username, Pageable pageable);
}
