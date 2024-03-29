package edu.ntnu.idatt2105.quizapp.testdata;

import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Answer;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizQuestion;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizRepository;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class for adding test data to the database. This class should only be used for testing purposes.
 */
public class QuizTestData {


  public static void addTestData(PasswordEncoder passwordEncoder,
                                 UserRepository userRepository,
                                 QuizRepository quizRepository) {

    User admin = User.builder()
        .username("Admin")
        .password(passwordEncoder.encode("password"))
        .role(Role.ADMIN)
        .build();


    User user2 = User.builder()
        .username("Geir1")
        .password(passwordEncoder.encode("password"))
        .role(Role.USER)
        .build();

    User emptyUser = User.builder()
        .username("a")
        .password(passwordEncoder.encode("a"))
        .role(Role.USER)
        .build();

    userRepository.save(admin);
    userRepository.save(user2);
    userRepository.save(emptyUser);

    addTestDataToUser(admin, quizRepository);
    addTestDataToUser(user2, quizRepository);
  }

  public static void addTestDataToUser(User user, QuizRepository quizRepository) {
    int randomIntBetween20And30 = (int) (Math.random() * 20) + 10;
    for (int i = 0; i < randomIntBetween20And30; i++) {

      Quiz quiz = Quiz.builder()
          .name("Example quiz")
          .description("random desc: " + (randomIntBetween20And30 + i))
          .author(user)
          .isOpen((i % 2) == 0)
          .build();

      QuizQuestion quizQuestion = QuizQuestion.builder()
          .quiz(quiz)
          .questionText("What is the capital of Norway?")
          .build();


      List<Answer> exampleAnswers = List.of(
          Answer.builder()
              .quizQuestion(quizQuestion)
              .answerText("Oslo")
              .isCorrect(true)
              .build(),
          Answer.builder()
              .answerText("Bergen")
              .quizQuestion(quizQuestion)
              .isCorrect(false)
              .build(),
          Answer.builder()
              .answerText("Trondheim")
              .quizQuestion(quizQuestion)

              .isCorrect(false)
              .build(),
          Answer.builder()
              .answerText("Stavanger")
              .quizQuestion(quizQuestion)
              .isCorrect(false)
              .build()
      );

      QuizQuestion quizQuestion2 = QuizQuestion.builder()
          .quiz(quiz)
          .questionText("5 == 5?")
          .build();

      List<Answer> exampleAnswers2 = List.of(
          Answer.builder()
              .quizQuestion(quizQuestion2)
              .answerText("true")
              .isCorrect(true)
              .build(),
          Answer.builder()
              .answerText("false")
              .quizQuestion(quizQuestion2)
              .isCorrect(false)
              .build()
      );

      quizQuestion.setAnswers(exampleAnswers);
      quizQuestion2.setAnswers(exampleAnswers2);
      quiz.setQuestions(List.of(quizQuestion, quizQuestion2));


      System.out.println("Adding quiz: size" + quiz.getQuestions().size());
      quizRepository.save(quiz);
    }
  }

}
