package edu.ntnu.idatt2105.quizapp.services.user;

import edu.ntnu.idatt2105.quizapp.services.JwtService;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import edu.ntnu.idatt2105.quizapp.model.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for JwtService and key generation.
 */
class JwtServiceTest {
  static JwtService jwtService;
  static User testUser;
  static String testToken;

  @BeforeAll
  static void beforeAll() {
    testUser = TestUtil.createUserA();
    jwtService = new JwtService();
    testToken = jwtService.generateToken(testUser);
  }

  @Test
  void JwtService_ExtractClaim_ReturnClaim() {
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
  void JwtService_GenerateToken_ReturnToken() {
    //Act
    String token = jwtService.generateToken(testUser);

    //Assert
    assertNotNull(token);
    assertFalse(token.isBlank());
    assertFalse(token.isEmpty());
  }

  @Test
  void JwtService_isTokenValid_ReturnTrue() {
    //Arrange
    String generatedToken = jwtService.generateToken(testUser);

    //Act
    boolean isTokenValid = jwtService.isTokenValid(generatedToken, testUser);

    //Assert
    assertTrue(isTokenValid);
  }

  @Test
  void JwtService_isTokenValid_ReturnFalse() {
    //Arrange
    String generatedToken = jwtService.generateToken(testUser);
    User differentUser = TestUtil.createUserB();

    //Act
    boolean isTokenValid = jwtService.isTokenValid(generatedToken, differentUser);

    //Assert
    assertFalse(isTokenValid);
  }

  @Test
  void JwtService_ExtractUsername_ReturnUsername() {
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