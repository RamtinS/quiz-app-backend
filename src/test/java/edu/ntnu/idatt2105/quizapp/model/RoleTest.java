package edu.ntnu.idatt2105.quizapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for the role enum.
 *
 * @author Jytabiri
 * @version 1.0
 * @since 2024-03-25
 */
class RoleTest {
  @Test
  void testEnumValues() {
    // Assert
    assertEquals("USER", Role.USER.name());
    assertEquals("ADMIN", Role.ADMIN.name());
  }

}
