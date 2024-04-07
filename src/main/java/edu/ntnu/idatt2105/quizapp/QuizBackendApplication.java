package edu.ntnu.idatt2105.quizapp;

import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.CategoryRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizAttemptRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizRepository;
import edu.ntnu.idatt2105.quizapp.testdata.QuizTestData;
import lombok.extern.slf4j.Slf4j;
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
 * @author Tobias Oftedal
 * @author Jeffrey Yaw Annor Tabiri
 * @author Ramtin Samavat
 * @version 1.0
 */
@Slf4j
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


      if (args.length > 0) {
        log.info("Running with argument: " + args[0]);
        if (args[0].equalsIgnoreCase("--data")) {
          QuizTestData.addTestData(userRepository, passwordEncoder, quizRepository,
              categoryRepository, quizAttemptRepository);
        } else if (args[0].equalsIgnoreCase("--help")) {
          log.info("Possible options: ");
          log.info("data: Add test data to the database to be able to run existing " +
              "quizzes");
        } else {
          log.error(args[0] + " is not a valid argument. Please try running with '--help' " +
              "to see possible options.");
        }
      } else {
        log.info("No arguments given, running without adding any data except categories");
        QuizTestData.createAndSaveCategories(categoryRepository);
      }
    };
  }
}