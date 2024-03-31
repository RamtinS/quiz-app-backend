package edu.ntnu.idatt2105.quizapp.services;

import edu.ntnu.idatt2105.quizapp.dto.user.EditUserDto;
import edu.ntnu.idatt2105.quizapp.dto.user.UserDetailsDto;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

  //Password encoder to hash passwords in a database.
  private final PasswordEncoder passwordEncoder;

  /**
   * The method updates the user information of an existing user in the database.
   *
   * @param username The username of the user to be updated.
   * @param editUserDto The DTO containing the new user information.
   * @throws UsernameNotFoundException If the user is not found in the database.
   * @throws IllegalArgumentException If email already in use by another user.
   */
  public void editUser(@NonNull String username, @NonNull EditUserDto editUserDto)
          throws UsernameNotFoundException, IllegalArgumentException {

    User user = userRepository.findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                    "User with username " + username + " not found."));

    if (editUserDto.getNewEmail() != null && !editUserDto.getNewEmail().isBlank()) {

      Optional<User> existingUserWithEmail = userRepository.findUserByEmail(editUserDto.getNewEmail());
      if (existingUserWithEmail.isPresent() && !existingUserWithEmail.get().getUsername().equals(user.getUsername())) {
        throw new IllegalArgumentException("Email already in use by another user.");
      }

      user.setEmail(editUserDto.getNewEmail());
    }

    if (editUserDto.getNewPassword() != null && !editUserDto.getNewPassword().isBlank()) {
      user.setPassword(passwordEncoder.encode(editUserDto.getNewPassword()));
    }

    if (editUserDto.getNewName() != null && !editUserDto.getNewName().isBlank()) {
      user.setName(editUserDto.getNewName());
    }

    if (editUserDto.getNewSurname() != null && !editUserDto.getNewSurname().isBlank()) {
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
    User user = userRepository.findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                    "User with username " + username + " not found."));

    return UserDetailsDto.builder()
            .email(user.getEmail())
            .name(user.getName())
            .surname(user.getSurName())
            .build();

  }
}
