package edu.ntnu.idatt2105.quizapp.services;

import edu.ntnu.idatt2105.quizapp.dto.RegistrationDto;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import edu.ntnu.idatt2105.quizapp.dto.AuthenticationDto;
import edu.ntnu.idatt2105.quizapp.dto.LoginDto;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

  @InjectMocks
  AuthenticationService authenticationService;

  @Mock
  UserRepository userRepository;

  @Mock
  AuthenticationManager authenticationManager;

  @Mock
  BCryptPasswordEncoder passwordEncoder;
  @Mock
  JwtService jwtService;

  @Test
  void AuthenticationService_RegisterUser_ReturnRegisterDto() {
    //Arrange
    RegistrationDto registrationDto = TestUtil.createRegistrationDtoA();
    when(passwordEncoder.encode(registrationDto.getPassword())).thenReturn("Password");
    when(jwtService.generateToken(any(User.class))).thenReturn("MockToken");

    //Act
    AuthenticationDto authenticationDto = authenticationService.registerUser(registrationDto);

    //Assert
    assertNotNull(authenticationDto.getToken());
    assertFalse(authenticationDto.getToken().isBlank());
  }

  @Test
  void AuthenticationService_AuthenticateUser_ReturnLoginDto() {
    //Arrange
    LoginDto mockLogin = TestUtil.createLoginDtoA();
    User mockUser = TestUtil.createUserA();
    when(userRepository.findUserByUsername("Mark")).thenReturn(Optional.ofNullable(mockUser));
    when(jwtService.generateToken(any(User.class))).thenReturn("MockToken");

    //Act
    AuthenticationDto authenticationDto = authenticationService.authenticateUser(mockLogin);

    //Assert
    assertNotNull(authenticationDto.getToken());
    assertFalse(authenticationDto.getToken().isBlank());
  }
}