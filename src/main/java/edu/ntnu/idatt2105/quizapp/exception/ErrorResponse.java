package edu.ntnu.idatt2105.quizapp.exception;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

/**
 * The class represents an error response containing an error message.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
@Data
public class ErrorResponse {

  @NonNull
  @NotBlank
  private final String errorMessage;
}
