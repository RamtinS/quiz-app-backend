package edu.ntnu.idatt2105.quizapp;

import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;

public class TestUtil {

  public static User createUserA() {
    return User.builder()
            .username("Mark")
            .password("Password")
            .build();
  }

  public static Role createRoleA() {
    return Role.builder()
            .authority("USER")
            .build();
  }

}
