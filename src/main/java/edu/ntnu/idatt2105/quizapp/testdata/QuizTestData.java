package edu.ntnu.idatt2105.quizapp.testdata;

import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Answer;
import edu.ntnu.idatt2105.quizapp.model.quiz.Category;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizQuestion;
import edu.ntnu.idatt2105.quizapp.model.quiz.Tag;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.CategoryRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizRepository;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class for adding test data to the database. This class should only be used for testing purposes.
 */
public class QuizTestData {

  public static void addTestData(PasswordEncoder passwordEncoder,
                                 UserRepository userRepository,
                                 QuizRepository quizRepository,
                                CategoryRepository categoryRepository ) {


    User admin = User.builder()
            .username("Admin")
            .password(passwordEncoder.encode("password"))
            .email("admin@ntnu.edu")
            .name("Test")
            .surName("Test")
            .role(Role.ADMIN)
            .build();


    User user2 = User.builder()
            .username("Geir1")
            .password(passwordEncoder.encode("password"))
            .email("geir1@ntnu.edu")
            .name("Test")
            .surName("Test")
            .role(Role.USER)
            .build();

    User emptyUser = User.builder()
            .username("aaaa")
            .password(passwordEncoder.encode("a"))
            .email("a@ntnu.edu")
            .name("Test")
            .surName("Test")
            .role(Role.USER)
            .build();

    userRepository.save(admin);
    userRepository.save(user2);
    userRepository.save(emptyUser);

    addTestDataToUser(admin, quizRepository, categoryRepository);
    addTestDataToUser(user2, quizRepository, categoryRepository);
  }

  public static void addTestDataToUser(User user, QuizRepository quizRepository, CategoryRepository categoryRepository) {
    Category exampleCategory = Category.builder().description("Food").build();
    Category exampleCategory2 = Category.builder().description("Sports").build();
    Category exampleCategory3 = Category.builder().description("Gaming").build();
    categoryRepository.save(exampleCategory);
    categoryRepository.save(exampleCategory2);
    categoryRepository.save(exampleCategory3);
    int randomIntBetween20And30 = (int) (Math.random() * 20) + 10;
    for (int i = 0; i < 1; i++) {

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

      List<Tag> exampleTag = List.of(
              Tag.builder().description("Mathematical").build(),
              Tag.builder().description("Physics").build()
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

      quiz.setCategory(exampleCategory);
      quizQuestion.setAnswers(exampleAnswers);
      quizQuestion2.setAnswers(exampleAnswers2);
      quiz.setQuestions(List.of(quizQuestion, quizQuestion2));
      quiz.setTags(exampleTag);

      System.out.println("Adding quiz: size" + quiz.getQuestions().size());
      Long id = quizRepository.save(quiz).getId();
      Quiz quiz1 = quizRepository.findQuizById(id).get();

      System.out.println(quiz1.getCategory().getDescription());

      System.out.println(quiz1.getTags());

      //System.out.println(quizRepository.findQuizByCategoryId(1L).size());


      //System.out.println(quizRepository.findQuizByTagsDescription("Physics").get(0).getId());
    }
  }

}
