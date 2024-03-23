package edu.ntnu.idatt2105.quizapp.services;


import edu.ntnu.idatt2105.quizapp.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {
  private final String SECRET_KEY = "SSSSSSSSSSSSSEEassssssssssssssssssssssssssssssssssssssssssdddddddddEECRETKEY";

  /**
   * Extracts a claim/information from a JWT token.
   *
   * @param token    is the JWT token to extract information from.
   * @param resolver A function that takes a Claims object and returns a T object representing the desired claim.
   * @param <T>      The type of the desired claim
   * @return The claim represented by a T object
   */
  public <T> T extractClaim(String token, Function<Claims, T> resolver) {
    Claims claims = extractAllClaims(token);
    return resolver.apply(claims);
  }

  /**
   * Method to generate token.
   *
   * @param user generate token specified for the user.
   * @return a string represetning the JWT token.
   */
  public String generateToken(User user) {

    log.info("Generating token to user {}", user.getUsername());

    return Jwts
            .builder()
            .subject(user.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
            .signWith(getSignKey())
            .compact();
  }

  /**
   * Check to see if the token is valid.
   *
   * @param token       a JWT token to be checked
   * @param userDetails containing a relationship to a user entity object.
   * @return true if valid, false if not.
   */
  public boolean isTokenValid(String token, UserDetails userDetails) {

    log.info("Checking validity of token to user {}", userDetails.getUsername());

    String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }


  /**
   * Checks if given token is expired.
   *
   * @param token the token to check.
   * @return true if expired, false if not.
   */
  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }


  /**
   * A method to extract all claims/information regarding a specific token.
   *
   * @param token is the token in which the claims are to be extracted out off.
   * @return A set of claims regarding a token.
   */
  private Claims extractAllClaims(String token) {
    return Jwts
            .parser()
            .verifyWith(getSignKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }


  /**
   * Extract the expiration date from a JWT token.
   *
   * @param token is the JWT token to extract its expiration date from.
   * @return a date object representing the expiration of a token.
   */
  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  /**
   * Extract the username from a JWT token.
   *
   * @param token the JWT token to extract the username from.
   * @return a username associated with the specific token.
   */
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  /**
   * Get the signing key used to sign JWT Tokens.
   *
   * @return A secret key object, which contains byte information of the secret security constant.
   */
  private SecretKey getSignKey() {
    byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}
