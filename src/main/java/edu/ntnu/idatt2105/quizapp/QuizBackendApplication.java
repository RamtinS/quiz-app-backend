package edu.ntnu.idatt2105.quizapp;

import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.CategoryRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizAttemptRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizRepository;
import edu.ntnu.idatt2105.quizapp.testdata.QuizTestData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The main class of the application.
 * This class is responsible for starting the Spring Boot application.
 * The class also contains a CommandLineRunner bean that runs on startup.
 *
 * @version 1.0
 * @author Tobias Oftedal
 * @author Jeffrey Yaw Annor Tabiri
 * @author Ramtin Samavat
 */
@SpringBootApplication
public class QuizBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuizBackendApplication.class, args);
  }

  @Bean
  CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder,
                        QuizRepository quizRepository, CategoryRepository categoryRepository,
                        QuizAttemptRepository quizAttemptRepository) {

    return args -> {

      QuizTestData.addTestData(passwordEncoder, userRepository, quizRepository,
              categoryRepository, quizAttemptRepository);

    };
  }
}
