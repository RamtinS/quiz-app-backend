package edu.ntnu.idatt2105.quizapp;

import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizRepository;
import edu.ntnu.idatt2105.quizapp.testdata.QuizTestData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class QuizBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuizBackendApplication.class, args);
  }

  @Bean
  CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder,
                        QuizRepository quizRepository) {

    return args -> {

      QuizTestData.addTestData(passwordEncoder, userRepository, quizRepository);

      if (userRepository.findUserByUsername("Admin").isPresent()) {
        User testUser = userRepository.findUserByUsername("Admin").get();
        QuizTestData.addTestDataToUser(testUser, quizRepository);
      }
    };
  }
}
