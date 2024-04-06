package edu.ntnu.idatt2105.quizapp.config;

import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for the application's security settings.
 * This code is inspired by a tutorial on YouTube: <a href="https://www.youtube.com/watch?v=KxqlJblhzfI&t=3457s">...</a>
 *
 * @author Ramtin Samavat
 * @version 1.0
 * @since 2024-03-26
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final UserRepository userRepository;

  /**
   * The method defines a custom UserDetailsService bean to load user details for authentication.
   *
   * @return An implementation of UserDetailsService.
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return username -> userRepository.findUserByUsernameIgnoreCase(username)
            .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
  }

  /**
   * The method defines an AuthenticationProvider bean using DaoAuthenticationProvider for authentication.
   *
   * @return An implementation of AuthenticationProvider.
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService());
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  /**
   * The method defines an AuthenticationManager bean using the provided AuthenticationConfiguration.
   *
   * @param config The AuthenticationConfiguration to configure the AuthenticationManager.
   * @return An implementation of AuthenticationManager.
   * @throws Exception If an error occurs while configuring the AuthenticationManager.
   */
  @Bean
  public AuthenticationManager authenticationManager(@NonNull AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  /**
   * The method defines a PasswordEncoder bean using BCryptPasswordEncoder for password encoding.
   *
   * @return An implementation of PasswordEncoder.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
