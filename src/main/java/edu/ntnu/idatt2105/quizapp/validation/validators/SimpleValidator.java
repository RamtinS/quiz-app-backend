package edu.ntnu.idatt2105.quizapp.validation.validators;

/**
 * The class provides basic validation for user input.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
public class SimpleValidator {

  /**
   * The method checks if the provided string is not null and not blank.
   *
   * @param string The string to be validated.
   * @return true if the string is valid, false otherwise.
   */
  public static boolean isNotNullOrBlank(String string) {
    return string != null && !string.isBlank();
  }
}
