package edu.ntnu.idatt2105.quizapp.testdata;

import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Answer;
import edu.ntnu.idatt2105.quizapp.model.quiz.Category;
import edu.ntnu.idatt2105.quizapp.model.quiz.MultipleChoiceQuestion;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;
import edu.ntnu.idatt2105.quizapp.model.quiz.TrueOrFalseQuestion;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.CategoryRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizAttemptRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class for adding test data to the database.
 * Should not be included in production code.
 * Should not be seen as a part of the testing profile
 *
 * @author Tobias Oftedal
 */
@Slf4j
public class QuizTestData {

  /**
   * List of possible categories for the quizzes.
   */
  private static final String[] possibleCategories = {
      "Miscellaneous", "Food", "Sports", "Gaming", "Math", "Physics", "Chemistry"
  };

  /**
   * Adds test data to the database.
   *
   * @param userRepository        The user repository
   * @param passwordEncoder       The password encoder
   * @param quizRepository        The quiz repository
   * @param categoryRepository    The category repository
   * @param quizAttemptRepository The quiz attempt repository
   */
  public static void addTestData(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 QuizRepository quizRepository,
                                 CategoryRepository categoryRepository,
                                 QuizAttemptRepository quizAttemptRepository) {
    try {
      List<Category> categories = createAndSaveCategories(categoryRepository);
      List<User> users = createTestUsers(passwordEncoder, userRepository);
      addCypressUser(passwordEncoder, userRepository);
      users.add(addAdminUser(passwordEncoder, userRepository));
      for (int i = 0; i < users.size(); i++) {
        User currentUser = users.get(i);

        Category category = categories.get(i % categories.size());

        Quiz quiz = createQuiz(category);

        currentUser.setQuizzes(List.of(quiz));
        quiz.setAuthor(currentUser);


        quizRepository.save(quiz);
        userRepository.save(currentUser);

        List<QuizAttempt> quizAttempts = createQuizAttempts();
        quizAttempts.forEach(quizAttempt -> {
          quizAttempt.setUser(currentUser);
          quizAttempt.setQuiz(quiz);
          quizAttemptRepository.save(quizAttempt);
        });
      }
    } catch (Exception e) {
      log.error("Error adding test data: ", e);
    }
  }

  /**
   * Creates and saves categories to the database.
   *
   * @param categoryRepository The category repository
   * @return A list of the created categories
   */
  public static List<Category> createAndSaveCategories(CategoryRepository categoryRepository) {
    List<Category> categories = new ArrayList<>();
    for (String category : possibleCategories) {
      Category createdCategory = Category.builder().description(category).build();
      if (categoryRepository.findCategoryByDescription(category).isEmpty()) {
        Category savedCategory = categoryRepository.save(createdCategory);
        categories.add(savedCategory);
      }
    }
    return categories;
  }

  /**
   * Creates test users and saves them to the database.
   *
   * @param passwordEncoder The password encoder
   * @param userRepository  The user repository
   * @return A list of the created users
   */
  private static List<User> createTestUsers(PasswordEncoder passwordEncoder,
                                            UserRepository userRepository) {
    ArrayList<User> users = new ArrayList<>();

    for (int i = 1; i < 10; i++) {
      User user = User.builder()
          .username("user" + i).password(passwordEncoder.encode("password"))
          .email("user" + i + "@ntnu.no").name("Test").surName("Test").role(Role.USER)
          .quizzes(new ArrayList<>())
          .build();
      if (userRepository.findUserByUsernameIgnoreCase(user.getUsername()).isEmpty()) {
        User savedUser = userRepository.save(user);
        users.add(savedUser);
      }
    }
    return users;
  }

  /**
   * Adds an admin user to the database.
   *
   * @param passwordEncoder The password encoder
   * @param userRepository  The user repository
   * @return The created admin user
   */
  private static User addAdminUser(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    User admin = User.builder()
        .username("admin").password(passwordEncoder.encode("password"))
        .email("admin@ntnu.edu").name("Test").surName("Test").role(Role.ADMIN)
        .build();
    userRepository.save(admin);
    return admin;
  }

  /**
   * Adds a cypress user to the database.
   *
   * @param passwordEncoder The password encoder
   * @param userRepository  The user repository
   */
  private static void addCypressUser(PasswordEncoder passwordEncoder,
                                     UserRepository userRepository) {
    User emptyUser = User.builder() // Do not change. Is used for testing in frontend.
        .username("testUser")
        .password(passwordEncoder.encode("password"))
        .email("test@test.no")
        .name("Test")
        .surName("Test")
        .role(Role.USER)
        .build();
    userRepository.save(emptyUser);
  }

  /**
   * Creates a quiz with questions and 1 answer of each type.
   *
   * @param category The category of the quiz
   * @return The created quiz
   */
  private static Quiz createQuiz(Category category) {

    Quiz quiz = Quiz.builder()
        .name("Test quiz")
        .description("This is a test quiz")
        .isOpen(true)
        .category(category)
        .build();

    MultipleChoiceQuestion multipleChoiceQuestion = createMultipleChoiceQuestion();
    TrueOrFalseQuestion trueOrFalseQuestion = createTrueOrFalseQuestion();

    multipleChoiceQuestion.setQuiz(quiz);
    trueOrFalseQuestion.setQuiz(quiz);
    quiz.setQuestions(List.of(multipleChoiceQuestion, trueOrFalseQuestion));
    return quiz;
  }

  /**
   * Creates a multiple choice question with 4 answers.
   *
   * @return The created multiple choice question
   */
  private static MultipleChoiceQuestion createMultipleChoiceQuestion() {
    MultipleChoiceQuestion multipleChoiceQuestion = MultipleChoiceQuestion.builder()
        .questionText("Where is Gløshaugen located?")
        .build();

    List<Answer> answers = List.of(
        Answer.builder()
            .question(multipleChoiceQuestion)
            .answerText("Oslo")
            .isCorrect(false)
            .build(),
        Answer.builder()
            .question(multipleChoiceQuestion)
            .answerText("Bergen")
            .isCorrect(false)
            .build(),
        Answer.builder()
            .question(multipleChoiceQuestion)
            .answerText("Trondheim")
            .isCorrect(true)
            .build(),
        Answer.builder()
            .question(multipleChoiceQuestion)
            .answerText("Stavanger")
            .isCorrect(false)
            .build()
    );

    answers.forEach(answer -> answer.setQuestion(multipleChoiceQuestion));
    multipleChoiceQuestion.setAnswers(answers);

    return multipleChoiceQuestion;
  }

  /**
   * Creates a true or false question.
   *
   * @return The created true or false question
   */
  private static TrueOrFalseQuestion createTrueOrFalseQuestion() {
    return TrueOrFalseQuestion.builder()
        .questionText("The sky is blue")
        .questionIsCorrect(true)
        .build();

  }

  /**
   * Creates 20 quiz attempts with random scores and timestamps from the last week.
   *
   * @return A list of the created quiz attempts
   */
  private static List<QuizAttempt> createQuizAttempts() {
    List<QuizAttempt> quizAttempts = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      QuizAttempt quizAttempt = QuizAttempt.builder()
          .score(i * i - 3 * i + 3)
          .timestamp(LocalDate.now().minusDays(new Random().nextInt(7) + 1))
          .build();
      quizAttempts.add(quizAttempt);
    }
    return quizAttempts;
  }


}
