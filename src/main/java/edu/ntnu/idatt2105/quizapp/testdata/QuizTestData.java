package edu.ntnu.idatt2105.quizapp.testdata;

import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Answer;
import edu.ntnu.idatt2105.quizapp.model.quiz.Category;
import edu.ntnu.idatt2105.quizapp.model.quiz.MultipleChoiceQuestion;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.model.quiz.Tag;
import edu.ntnu.idatt2105.quizapp.model.quiz.TrueOrFalseQuestion;
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
                          CategoryRepository categoryRepository) {


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
        .email("a@gmail.com")
        .password(passwordEncoder.encode("a"))
        .role(Role.USER)
        .build();

    userRepository.save(admin);
    userRepository.save(user2);
    userRepository.save(emptyUser);

    addTestDataToUser(admin, quizRepository, categoryRepository);
    addTestDataToUser(user2, quizRepository, categoryRepository);
  }

  public static void addTestDataToUser(User user, QuizRepository quizRepository,
                                       CategoryRepository categoryRepository) {
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

      quiz.setCategory(exampleCategory);
      quiz.setTags(exampleTag);
      quizRepository.save(quiz);


      System.out.println("Adding quiz: size" + quiz.getQuestions().size());
      Long id = quizRepository.save(quiz).getId();
      Quiz quiz1 = quizRepository.findQuizById(id).get();

      System.out.println(quiz1.getCategory().getDescription());

      System.out.println(quiz1.getTags());

//      System.out.println(quizRepository.findQuizByCategoryId(1L).size());
//
//
//      System.out.println(quizRepository.findQuizByTagsDescription("Physics").get(0).getId());
    }
  }

}
