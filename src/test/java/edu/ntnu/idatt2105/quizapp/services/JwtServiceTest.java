package edu.ntnu.idatt2105.quizapp.services;

import edu.ntnu.idatt2105.quizapp.TestUtil;
import edu.ntnu.idatt2105.quizapp.model.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {
  static JwtService jwtService;
  static User testUser;
  String testToken;

  @BeforeAll
  static void beforeAll() {
    testUser = TestUtil.createUserA();
    jwtService = new JwtService();
  }

  @BeforeEach
  void setUp() {
    testToken = jwtService.generateToken(testUser);
  }

  @Test
  void extractClaim() {
    //Arrange
    String expected = testUser.getUsername();
    String token = jwtService.generateToken(testUser);

    //Act
    Function<Claims, String> resolver = Claims::getSubject;
    String actual = jwtService.extractClaim(token, resolver);

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void jwtService_GenerateToken_ReturnToken() {
    //Act
    String token = jwtService.generateToken(testUser);

    //Assert
    assertNotNull(token);
    assertFalse(token.isBlank());
    assertFalse(token.isEmpty());
  }

  @Test
  void jwtService_isTokenValid_ReturnTrue() {
    //Arrange
    String generatedToken = jwtService.generateToken(testUser);

    //Act
    boolean isTokenValid = jwtService.isTokenValid(generatedToken, testUser);

    //Assert
    assertTrue(isTokenValid);
  }

  @Test
  void jwtService_isTokenValid_ReturnFalse() {
    //Arrange
    String generatedToken = jwtService.generateToken(testUser);
    User differentUser = TestUtil.createUserB();

    boolean isTokenValid = jwtService.isTokenValid(generatedToken, differentUser);

    assertFalse(isTokenValid);
  }

  @Test
  void extractUsername() {
    //Arrange
    String expected = testUser.getUsername();
    String token = jwtService.generateToken(testUser);

    //Act
    Function<Claims, String> resolver = Claims::getSubject;
    String actual = jwtService.extractUsername(token);

    //Assert
    assertEquals(expected, actual);
  }
}