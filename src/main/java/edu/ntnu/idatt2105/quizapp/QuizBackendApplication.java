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

  /**
   * A CommandLineRunner bean that runs on startup. The commandline runner should not
   * be used in production. It is only used for testing purposes.
   *
   * @param userRepository        the user repository
   * @param passwordEncoder       the password encoder
   * @param quizRepository        the quiz repository
   * @param categoryRepository    the category repository
   * @param quizAttemptRepository the quiz attempt repository
   * @return a CommandLineRunner bean
   */
  @Bean
  CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder,
                        QuizRepository quizRepository, CategoryRepository categoryRepository,
                        QuizAttemptRepository quizAttemptRepository) {

    return args -> {
      if (args.length == 0) {
        QuizTestData.addTestData(userRepository, passwordEncoder, quizRepository,
            categoryRepository,
            quizAttemptRepository);
      } else if (args[0].equalsIgnoreCase("--noTestData")) {
        log.info("Running tests without adding data");
      } else if (args[0].equalsIgnoreCase("--deleteAllData")) {
        log.info("Deleting all data in the database");
        quizAttemptRepository.deleteAll();
        quizRepository.deleteAll();
        userRepository.deleteAll();
        categoryRepository.deleteAll();
      } else if (args[0].equalsIgnoreCase("--help")) {
        log.info(
            """
                Commands:
                --noTestData: Run the program without adding new data
                 --resetTestData: Drops all tables in the database""");
      } else {
        log.error("Invalid command. Use --help for a list of commands");
        System.exit(1);
      }
    };
  }


}