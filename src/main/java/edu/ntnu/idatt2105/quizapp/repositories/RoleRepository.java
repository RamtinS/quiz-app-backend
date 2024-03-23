package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing Role entities.
 * The interface extends JpaRepository.
 * The class allows for basic CRUD (Create, Read, Update, Delete) operations on role
 * entities.
 *
 * @version 1.0
 * @since 2024-03-22
 * @author Jytabiri
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  /**
   * A custom query search which allows us to search for roles by their authority.
   *
   * @param authority is the authority/permission to search by
   * @return a container object which could inhabit a role.
   */
  Optional<Role> findByAuthority(String authority);
}
