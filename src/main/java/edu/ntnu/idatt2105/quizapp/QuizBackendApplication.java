package edu.ntnu.idatt2105.quizapp;

import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class QuizBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuizBackendApplication.class, args);
  }

  @Bean
  CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {

    return args -> {

      User user = User.builder()
              .username("Admin")
              .password(passwordEncoder.encode("password"))
              .role(Role.ADMIN)
              .build();

      userRepository.save(user);
    };
  }
}
