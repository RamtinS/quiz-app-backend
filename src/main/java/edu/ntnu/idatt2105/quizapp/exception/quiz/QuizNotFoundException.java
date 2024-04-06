package edu.ntnu.idatt2105.quizapp.exception.quiz;

/**
 * The class represents a custom exception for when a quiz object does not exist.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
public class QuizNotFoundException extends RuntimeException {

  /**
   * Constructs an instance of the class with the specified error message.
   *
   * @param message The error message.
   */
  public QuizNotFoundException(String message) {
    super(message);
  }

  /**
   * Constructs an instance of the clas with a predefined error message.
   */
  public QuizNotFoundException() {
    super("Quiz not found.");
  }
}
