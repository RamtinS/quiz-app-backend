package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.warehouseapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing User entities.
 * The interface extends JpaRepository.
 * The class allows for basic CRUD (Create, Read, Update, Delete) operations on users.
 *
 * @version 1.0
 * @since 2024-03-22
 * @author Jytabiri
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findUserByUsername(String username);
}
