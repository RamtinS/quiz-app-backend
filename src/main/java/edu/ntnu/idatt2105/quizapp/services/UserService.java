package edu.ntnu.idatt2105.quizapp.services;

import edu.ntnu.idatt2105.quizapp.dto.PublicUserInformationDto;
import edu.ntnu.idatt2105.quizapp.dto.user.EditUserDto;
import edu.ntnu.idatt2105.quizapp.dto.user.UserDetailsDto;
import edu.ntnu.idatt2105.quizapp.dto.user.UserStatsDto;
import edu.ntnu.idatt2105.quizapp.exception.user.EmailAlreadyExistsException;
import edu.ntnu.idatt2105.quizapp.mapper.UserMapper;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizAttemptRepository;
import edu.ntnu.idatt2105.quizapp.validation.validators.UserValidator;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class that encapsulates the logic for handling user-related operations.
 * It uses the {@link UserRepository} to perform the operations in the database.
 *
 * @author Ramtin Samavat
 * @version  1.0
 */
@Service
@RequiredArgsConstructor
public class UserService {

  //CRUD operations on user models.
  private final UserRepository userRepository;

  // Mapper for mapping user DTOs to entities and vice versa.
  private final UserMapper userMapper;

  //Password encoder to hash passwords in a database.
  private final PasswordEncoder passwordEncoder;

  // Repository for managing quiz attempts.
  private final QuizAttemptRepository quizAttemptRepository;

  /**
   * The method updates the user information of an existing user in the database.
   *
   * @param username The username of the user to be updated.
   * @param editUserDto The DTO containing the new user information.
   * @throws UsernameNotFoundException If the user is not found in the database.
   * @throws EmailAlreadyExistsException If the email already exists.
   */
  public void editUser(@NonNull String username, @NonNull EditUserDto editUserDto)
          throws UsernameNotFoundException, IllegalArgumentException {

    editUserDto.setNewName(editUserDto.getNewName().replaceAll("\\s", "").toLowerCase());

    User user = userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() ->
            new UsernameNotFoundException("User with username " + username + " not found."));

    Optional<User> existingUserWithEmail = userRepository
            .findUserByEmailIgnoreCase(editUserDto.getNewEmail());

    if (existingUserWithEmail.isPresent() && !existingUserWithEmail
            .get().getUsername().equals(user.getUsername())) {
      throw new EmailAlreadyExistsException();
    }

    if (!editUserDto.getNewEmail().isBlank()) {
      UserValidator.validateEmail(editUserDto.getNewEmail());
      user.setEmail(editUserDto.getNewEmail());
    }

    if (!editUserDto.getNewPassword().isBlank()) {
      UserValidator.validatePassword(editUserDto.getNewPassword());
      user.setPassword(passwordEncoder.encode(editUserDto.getNewPassword()));
    }

    if (!editUserDto.getNewName().isBlank()) {
      UserValidator.validateName(editUserDto.getNewName());
      user.setName(editUserDto.getNewName());
    }

    if (!editUserDto.getNewSurname().isBlank()) {
      UserValidator.validateSurname(editUserDto.getNewSurname());
      user.setSurName(editUserDto.getNewSurname());
    }

    userRepository.save(user);
  }

  /**
   * The method retrieves the user details for the specified username.
   *
   * @param username The username of the user to retrieve the information for.
   * @return The UserDetailsDto containing user details.
   * @throws UsernameNotFoundException If the user is not found in the database.
   */
  public UserDetailsDto getUserDetails(@NonNull String username) throws UsernameNotFoundException {
    User user = userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() ->
            new UsernameNotFoundException("User with username " + username + " not found."));

    return UserDetailsDto.builder()
            .email(user.getEmail())
            .name(user.getName())
            .surname(user.getSurName())
            .build();

  }

  /**
   * Finds a list of public profile DTOs based on the search parameter.
   *
   * @param searchString the search parameter.
   * @param pageable the pageable used to find a specified page.
   * @return a list of public profile DTOs based on the search parameter.
   */
  public List<PublicUserInformationDto> findPublicProfilesFromUsername(
          String searchString, Pageable pageable) {

    return userRepository.findAllByUsernameContainingIgnoreCase(searchString, pageable)
            .stream().map(userMapper::mapToPublicUserInformation).toList();
  }

  /**
   * The method retrieves the user's quiz statistics.
   *
   * @param username The username of the user to retrieve the data for.
   * @return The UserStatsDto containing the user statistics.
   */
  public UserStatsDto getUserStats(@NonNull String username) {
    LocalDate today = LocalDate.now();
    LocalDate sevenDaysAgo = today.minusDays(7);

    Map<LocalDate, Long> attemptsLastSevenDays = new TreeMap<>();
    for (int i = 0; i < 7; i++) {
      attemptsLastSevenDays.put(today.minusDays(i), 0L);
    }

    List<QuizAttempt> quizAttempts = quizAttemptRepository.findQuizAttemptByUser_Username(username);

    List<QuizAttempt> attemptsLastDays = quizAttempts.stream()
            .filter(attempt -> attempt.getTimestamp().isAfter(sevenDaysAgo))
            .toList();

    for (QuizAttempt attempt : attemptsLastDays) {
      LocalDate attemptDate = attempt.getTimestamp().atStartOfDay().toLocalDate();
      attemptsLastSevenDays.put(attemptDate, attemptsLastSevenDays.get(attemptDate) + 1);
    }

    int totalAttempts = quizAttempts.size();

    int totalScore = quizAttempts.stream().mapToInt(QuizAttempt::getScore).sum();

    return UserStatsDto.builder()
            .quizAttemptsLastSevenDays(attemptsLastSevenDays)
            .totalQuizAttempts(totalAttempts)
            .totalScore(totalScore).build();
  }

  /**
   * Retrieves the public user information for the specified username.
   *
   * @param username The username of the user to retrieve the public information for.
   * @return The PublicUserInformationDTO containing the public user information.
   * @throws UsernameNotFoundException If the user is not found in the database.
   */
  public PublicUserInformationDto getPublicUserInformation(String username) {
    User user = userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() ->
            new UsernameNotFoundException("User with username " + username + " not found."));

    return userMapper.mapToPublicUserInformation(user);
  }
}
