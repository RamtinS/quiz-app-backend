package edu.ntnu.idatt2105.quizapp.validation.rules;

import lombok.Getter;

/**
 * Enum containing validation rules for user attributes.
 *
 * @author Ramtin Samavat
 * @version 1.0
 */
@Getter
public enum UserValidationRules {

  /**
   * Regular expression for validating usernames.
   */
  USERNAME("^[a-zA-Z0-9_-]{4,32}$"),

  /**
   * Regular expression for validating passwords.
   */
  PASSWORD("^.{8,}$"),

  /**
   * Regular expression for validating email addresses.
   */
  EMAIL("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,128}$"),

  /**
   * Regular expression for validating names.
   */
  NAME("^[a-zA-Z]{1,64}$"),

  /**
   * Regular expression for validating surnames.
   */
  SURNAME("^[a-zA-Z]{1,64}$");

  private final String regex;

  /**
   * Constructor for UserValidationRules enum.
   *
   * @param regex The regular expression associated with the rule.
   */
  UserValidationRules(String regex) {
    this.regex = regex;
  }
}
