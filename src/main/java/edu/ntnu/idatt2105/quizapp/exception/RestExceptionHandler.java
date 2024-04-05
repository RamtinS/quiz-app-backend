package edu.ntnu.idatt2105.quizapp.exception;

import edu.ntnu.idatt2105.quizapp.exception.user.EmailAlreadyExistsException;
import edu.ntnu.idatt2105.quizapp.exception.user.UsernameAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The class is a global exception handler for REST controllers.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * The method handles exceptions related to username or email already existing.
   *
   * @param ex The exception to handle.
   * @return ResponseEntity containing the ErrorResponse with HTTP status code 409 (CONFLICT).
   */
  @ExceptionHandler({UsernameAlreadyExistsException.class, EmailAlreadyExistsException.class})
  public ResponseEntity<ErrorResponse> handleConflict(Exception ex) {
    String errorMessage = ex.getMessage();
    return buildResponseEntityWithErrorResponse(ex, errorMessage, HttpStatus.CONFLICT);
  }

  /**
   * The method handles situations where the input value is not accepted.
   *
   * @param ex The exception to handle.
   * @return ResponseEntity containing the ErrorResponse with HTTP status code 400 (BAD_REQUEST).
   */
  @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
  public ResponseEntity<ErrorResponse> handelNullPointerException(Exception ex) {
    String errorMessage = ex.getMessage();
    return buildResponseEntityWithErrorResponse(ex, errorMessage, HttpStatus.BAD_REQUEST);
  }

  /**
   * The method handles exceptions from when a user authentication fails due to bad credentials.
   *
   * @param ex The exception to handle.
   * @return ResponseEntity containing the ErrorResponse with HTTP status code 401 (UNAUTHORIZED).
   */
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentialsException(Exception ex) {
    String errorMessage = "User authentication failed.";
    return buildResponseEntityWithErrorResponse(ex, errorMessage, HttpStatus.UNAUTHORIZED);
  }

  /**
   * The method handles exceptions when a user based on the username is not found.
   *
   * @param ex The exception to handle.
   * @return ResponseEntity containing the ErrorResponse with HTTP status code 404 (NOT_FOUND).
   */
  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(Exception ex) {
    String errorMessage = ex.getMessage();
    return buildResponseEntityWithErrorResponse(ex, errorMessage, HttpStatus.NOT_FOUND);
  }

  /**
   * The method handles exceptions related to optimistic locking failure.
   *
   * @param ex The exception to handle.
   * @return ResponseEntity containing the ErrorResponse with HTTP status code 404 (NOT_FOUND).
   */
  @ExceptionHandler(OptimisticLockingFailureException.class)
  public ResponseEntity<ErrorResponse> handleOptimisticLockingFailureException(Exception ex) {
    String errorMessage = "The entity could not be found";
    return buildResponseEntityWithErrorResponse(ex, errorMessage, HttpStatus.NOT_FOUND);
  }

  /**
   * The method handles unexpected exceptions that may occur during the execution
   * of the application.
   *
   * @param ex The exception to handle.
   * @return ResponseEntity containing ErrorResponse with HTTP status 500 (INTERNAL_SERVER_ERROR).
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception ex) {
    log.error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());
    log.debug("Stack Trace:", ex);
    ErrorResponse errorResponse = new ErrorResponse("Internal Server Error");
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * The method builds a ResponseEntity containing an ErrorResponse based on the given
   * exception and HTTP status.
   *
   * @param ex The exception to handle.
   * @param status The HTTP status to set in the response.
   * @return ResponseEntity containing the ErrorResponse with the specified HTTP status.
   */
  private ResponseEntity<ErrorResponse> buildResponseEntityWithErrorResponse(
          Exception ex, String errorMessage, HttpStatus status) {
    log.error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());
    ErrorResponse errorResponse = new ErrorResponse(errorMessage);
    return new ResponseEntity<>(errorResponse, status);
  }
}
