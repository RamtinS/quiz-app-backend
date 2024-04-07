
package edu.ntnu.idatt2105.quizapp.services.user;

import edu.ntnu.idatt2105.quizapp.dto.PublicUserInformationDto;
import edu.ntnu.idatt2105.quizapp.dto.user.UserDetailsDto;
import edu.ntnu.idatt2105.quizapp.dto.user.UserStatsDto;
import edu.ntnu.idatt2105.quizapp.mapper.UserMapper;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizAttemptRepository;
import edu.ntnu.idatt2105.quizapp.services.UserService;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @InjectMocks
  UserService userService;

  User user;

  QuizAttempt quizAttempt;

  @Mock
  UserRepository userRepository;

  @Mock
  UserMapper userMapper;

  @Mock
  PasswordEncoder passwordEncoder;

  @Mock
  QuizAttemptRepository quizAttemptRepository;

  @BeforeEach
  void setUp() {
    user = TestUtil.createUserA();
    quizAttempt = TestUtil.createQuizAttemptA();
  }

  @Test
  void UserService_EditUser_ReturnEditUserDto() {
    //Arrange
    String expectedUsername = user.getUsername();
    String expectedEmail = user.getEmail();
    when(userRepository.findUserByUsernameIgnoreCase(expectedUsername)).thenReturn(Optional.of(user));
    when(userRepository.findUserByEmailIgnoreCase(expectedEmail)).thenReturn(Optional.of(user));
    when(passwordEncoder.encode(user.getPassword())).thenReturn("Password");

    //Act
    assertDoesNotThrow(() -> userService.editUser(expectedUsername, TestUtil.createEditUserDtoA()));
  }

  @Test
  void UserService_EditUserWhenUserDoesNotExist_ReturnEditUserDto() {
    //Arrange
    String expectedUsername = user.getUsername();
    when(userRepository.findUserByUsernameIgnoreCase(expectedUsername)).thenReturn(Optional.empty());

    //Act and Assert
    assertThrows(UsernameNotFoundException.class, () -> userService.editUser(expectedUsername, TestUtil.createEditUserDtoA()));
  }


  @Test
  void UserService_GetUserDetails_ReturnUserUserDetailsDto() {
    //Arrange
    when(userRepository.findUserByUsernameIgnoreCase(user.getUsername())).thenReturn(java.util.Optional.of(user));
    String expectedUsername = user.getName();
    String expectedEmail = user.getEmail();

    //Act
    UserDetailsDto userDetailsDto = userService.getUserDetails(user.getUsername());
    String actualUsername = userDetailsDto.getName();
    String actualEmail = userDetailsDto.getEmail();


    //Assert
    assertEquals(expectedUsername, actualUsername);
    assertEquals(expectedEmail, actualEmail);
  }

  @Test
  void UserService_GetUserDetailsWhenUserDoesNotExist_ReturnUserUserDetailsDto() {
    //Arrange
    String username = "Mark";
    when(userRepository.findUserByUsernameIgnoreCase(username)).thenReturn(java.util.Optional.empty());

    //Act and Assert
    assertThrows(UsernameNotFoundException.class, () -> userService.getUserDetails(username));
  }


  @Test
  void UserService_FindPublicProfilesFromUsername_ReturnPublicUserInformationDTO() {
    //Arrange
    Pageable pageable = Pageable.unpaged();
    String expectedUsername = user.getUsername();
    when(userRepository.findAllByUsernameContainingIgnoreCase(user.getUsername(), pageable)).thenReturn(List.of(user));
    when(userMapper.mapToPublicUserInformation(user)).thenReturn(PublicUserInformationDto.builder().username(user.getUsername()).build());


    //Act
    List<PublicUserInformationDto> publicUserInformationDtos = userService.findPublicProfilesFromUsername(user.getUsername(), pageable);
    String actualUsername = publicUserInformationDtos.getFirst().getUsername();

    //Assert
    assertEquals(expectedUsername, actualUsername);
  }

  @Test
  void UserService_GetUserStats_Return_UserStatsDto() {
    //Arrange
    QuizAttempt testQuizAttempt = QuizAttempt.builder()
            .score(10)
            .quiz(QuizModelTestUtil.createQuizA())
            .user(user)
            .timestamp(LocalDate.now())
            .build();

    when(quizAttemptRepository.findQuizAttemptByUser_Username(user.getUsername())).thenReturn(List.of(testQuizAttempt));
    int expectedScore = testQuizAttempt.getScore();

    //Act
    UserStatsDto userStatsDto = userService.getUserStats(user.getUsername());
    int actualScore = userStatsDto.getTotalScore();

    //Assert
    assertEquals(expectedScore, actualScore);
  }

  @Test
  void UserService_GetPublicUserInformation_ReturnPublicUserInformationDto() {
    //Arrange
    when(userRepository.findUserByUsernameIgnoreCase(user.getUsername())).thenReturn(java.util.Optional.of(user));
    when(userMapper.mapToPublicUserInformation(user)).thenReturn(PublicUserInformationDto.builder().username(user.getUsername()).build());
    String expectedUsername = user.getUsername();

    //Act
    PublicUserInformationDto publicUserInformationDto = userService.getPublicUserInformation(expectedUsername);

    //Assert
    assertEquals(expectedUsername, publicUserInformationDto.getUsername());
  }

  @Test
  void UserService_GetPublicUserDoesNotFindUser_ReturnException() {
    //Arrange
    String username = "Mark";
    when(userRepository.findUserByUsernameIgnoreCase(username)).thenReturn(java.util.Optional.empty());

    //Act and Assert
    assertThrows(UsernameNotFoundException.class, () -> userService.getPublicUserInformation(username));
  }

}
