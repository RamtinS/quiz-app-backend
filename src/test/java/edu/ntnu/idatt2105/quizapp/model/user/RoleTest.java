
package edu.ntnu.idatt2105.quizapp.model.user;

import edu.ntnu.idatt2105.quizapp.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for the role enum.
 * Tests the enum values.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 */
class RoleTest {
  @Test
  void Role_testEnumValues() {
    // Assert
    Assertions.assertEquals("USER", Role.USER.name());
    assertEquals("ADMIN", Role.ADMIN.name());
  }

}
