package edu.ntnu.idatt2105.quizapp.testdata;

import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Answer;
import edu.ntnu.idatt2105.quizapp.model.quiz.Category;
import edu.ntnu.idatt2105.quizapp.model.quiz.MultipleChoiceQuestion;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.model.quiz.Tag;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;
import edu.ntnu.idatt2105.quizapp.model.quiz.TrueOrFalseQuestion;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.CategoryRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizAttemptRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

public class QuizTestData {

  public static void addTestData(PasswordEncoder passwordEncoder,
                                 UserRepository userRepository,
                                 QuizRepository quizRepository,
                                 CategoryRepository categoryRepository,
                                 QuizAttemptRepository quizAttemptRepository) {

    Category exampleCategory = Category.builder().description("Food").build();
    Category exampleCategory2 = Category.builder().description("Sports").build();
    Category exampleCategory3 = Category.builder().description("Gaming").build();
    Category exampleCategory4 = Category.builder().description("Math").build();
    Category exampleCategory5 = Category.builder().description("Physics").build();
    Category exampleCategory6 = Category.builder().description("Chemistry").build();

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

    User emptyUser = User.builder() // Do not change. Is used for testing in frontend.
            .username("TestUser")
            .password(passwordEncoder.encode("password"))
            .email("test@test.no")
            .name("Test")
            .surName("Test")
            .role(Role.USER)
            .build();

    userRepository.save(admin);
    userRepository.save(user2);
    userRepository.save(emptyUser);



    categoryRepository.save(exampleCategory);
    categoryRepository.save(exampleCategory2);
    categoryRepository.save(exampleCategory3);
    categoryRepository.save(exampleCategory4);
    categoryRepository.save(exampleCategory5);
    categoryRepository.save(exampleCategory6);

    addTestDataToUser(admin, quizRepository, categoryRepository, quizAttemptRepository);
    addTestDataToUser(user2, quizRepository, categoryRepository, quizAttemptRepository);


  }

  public static void addTestDataToUser(User user, QuizRepository quizRepository,
                                       CategoryRepository categoryRepository,
                                       QuizAttemptRepository quizAttemptRepository) {


    categoryRepository.save(categoryRepository.findCategoryByDescription("Food").get());
    categoryRepository.save(categoryRepository.findCategoryByDescription("Sports").get());
    categoryRepository.save(categoryRepository.findCategoryByDescription("Gaming").get());
    int randomIntBetween20And30 = (int) (Math.random() * 20) + 10;
    for (int i = 0; i < 1; i++) {

      Quiz quiz = Quiz.builder()
              .name("Example quiz")
              .description("random desc: " + (randomIntBetween20And30 + i))
              .author(user)
              .isOpen(true)
              .build();

      Quiz savedQuiz = quizRepository.save(quiz);

      MultipleChoiceQuestion multipleChoiceQuestion = MultipleChoiceQuestion.builder()
              .quiz(quiz)
              .questionText("What is the capital of Norway?")
              .build();

      multipleChoiceQuestion.setQuiz(quiz);


      TrueOrFalseQuestion trueOrFalseQuestion = TrueOrFalseQuestion.builder()
              .quiz(quiz)
              .questionText("Is the earth flat?")
              .questionIsCorrect(false)
              .build();

      trueOrFalseQuestion.setQuiz(quiz);

      quiz.setQuestions(List.of(multipleChoiceQuestion, trueOrFalseQuestion));



      List<Answer> exampleAnswers = List.of(
              Answer.builder()
                      .question(multipleChoiceQuestion)
                      .answerText("Oslo")
                      .isCorrect(true)
                      .build(),
              Answer.builder()
                      .answerText("Bergen")
                      .question(multipleChoiceQuestion)
                      .isCorrect(false)
                      .build(),
              Answer.builder()
                      .answerText("Trondheim")
                      .question(multipleChoiceQuestion)

                      .isCorrect(false)
                      .build(),
              Answer.builder()
                      .answerText("Stavanger")
                      .question(multipleChoiceQuestion)
                      .isCorrect(false)
                      .build()
      );

      multipleChoiceQuestion.setAnswers(exampleAnswers);
      exampleAnswers.forEach(answer -> answer.setQuestion(multipleChoiceQuestion));

      List<Tag> exampleTag = List.of(
              Tag.builder().description("Mathematical").build(),
              Tag.builder().description("Physics").build()
      );

      quiz.setCategory(categoryRepository.findCategoryByDescription("food").get());
      quiz.setTags(exampleTag);
      quizRepository.save(quiz);


      System.out.println("Adding quiz: size" + quiz.getQuestions().size());
      Long id = quizRepository.save(quiz).getId();
      Quiz quiz1 = quizRepository.findQuizById(id).get();

      System.out.println(quiz1.getCategory().getDescription());

      System.out.println(quiz1.getTags());

      //Quiz attempts.
      QuizAttempt quizAttempt = QuizAttempt.builder()
              .quiz(quiz)
              .user(user)
              .score(2)
              .timestamp(LocalDate.now())
              .build();

      QuizAttempt quizAttempt1 = QuizAttempt.builder()
              .quiz(quiz)
              .user(user)
              .score(2)
              .timestamp(LocalDate.now())
              .build();

      QuizAttempt quizAttempt2 = QuizAttempt.builder()
              .quiz(quiz)
              .user(user)
              .score(2)
              .timestamp(LocalDate.of(2024, 4, 3))
              .build();

      QuizAttempt quizAttempt3 = QuizAttempt.builder()
              .quiz(quiz)
              .user(user)
              .score(2)
              .timestamp(LocalDate.of(2024, 4, 2))
              .build();

      QuizAttempt quizAttempt4 = QuizAttempt.builder()
              .quiz(quiz)
              .user(user)
              .score(2)
              .timestamp(LocalDate.of(2024, 4, 1))
              .build();

      QuizAttempt quizAttempt5 = QuizAttempt.builder()
              .quiz(quiz)
              .user(user)
              .score(2)
              .timestamp(LocalDate.of(2024, 3, 31))
              .build();

      QuizAttempt quizAttempt6 = QuizAttempt.builder()
              .quiz(quiz)
              .user(user)
              .score(2)
              .timestamp(LocalDate.of(2024, 3, 30))
              .build();

      quizAttemptRepository.save(quizAttempt5);
      quizAttemptRepository.save(quizAttempt6);
      quizAttemptRepository.save(quizAttempt);
      quizAttemptRepository.save(quizAttempt1);
      quizAttemptRepository.save(quizAttempt2);
      quizAttemptRepository.save(quizAttempt3);
      quizAttemptRepository.save(quizAttempt4);
    }
  }

}
