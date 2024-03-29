package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.model.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for managing User entities.
 * The interface extends JpaRepository.
 * The class allows for basic CRUD (Create, Read, Update, Delete) operations on users.
 *
 * @author Jeffrey Tabiri
 * @author Tobias Oftedal
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserByUsername(String username);

  List<User> findAllByUsernameContainingIgnoreCase(String username, Pageable pageable);
}
