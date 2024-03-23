package edu.ntnu.idatt2105.quizapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {
  @NonNull
  private String token;

}
