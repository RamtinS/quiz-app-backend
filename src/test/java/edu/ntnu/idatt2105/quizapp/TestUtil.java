package edu.ntnu.idatt2105.quizapp;

import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;

/**
 * Utility class for mock creation of models.
 *
 * @version 1.0
 * @since 2024-03-25
 * @author Jytabiri
 */
public class TestUtil {

  public static User createUserA() {
    return User.builder()
            .username("Mark")
            .password("Password")
            .build();
  }

  public static User createUserB() {
    return User.builder()
            .username("Buffalo")
            .password("Password")
            .build();
  }

  public static User createUserC() {
    return User.builder()
            .username("Zac")
            .password("Password")
            .build();
  }

  public static User createUserD() {
    return User.builder()
            .username("Geoffrey")
            .password("Password")
            .build();
  }


  public static Role createRoleA() {
    return Role.builder()
            .authority("USER")
            .build();
  }

  public static Role createRoleB() {
    return Role.builder()
            .authority("DIAMOND")
            .build();
  }

  public static Role createRoleC() {
    return Role.builder()
            .authority("ADMIN")
            .build();
  }

  public static Role createRoleD() {
    return Role.builder()
            .authority("GOLD")
            .build();
  }
}
