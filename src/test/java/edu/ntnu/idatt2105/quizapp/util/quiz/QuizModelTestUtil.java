package edu.ntnu.idatt2105.quizapp.util.quiz;

import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Answer;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizQuestion;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import java.util.List;

public class QuizModelTestUtil {

  public static Answer createAnswerA() {
    return Answer.builder()
        .id(1L)
        .answerText("Answer A")
        .isCorrect(true)
        .build();
  }

  public static Answer createAnswerB() {
    return Answer.builder()
        .id(2L)
        .answerText("Answer B")
        .isCorrect(false)
        .build();
  }

  public static QuizQuestion createQuizQuestionA() {
    return QuizQuestion.builder()
        .questionText("Question A")
        .answers(List.of(createAnswerA(), createAnswerB()))
        .build();
  }

  public static QuizQuestion createQuizQuestionB() {
    return QuizQuestion.builder()
        .questionText("Question B")
        .answers(List.of(createAnswerA(), createAnswerB()))
        .build();
  }


  public static Quiz createQuizA() {
    return Quiz.builder()
        .id(1L)
        .author(TestUtil.createUserA())
        .name("Quiz A")
        .description("Description A")
        .questions(List.of(createQuizQuestionA(), createQuizQuestionB()))
        .isOpen(true)
        .build();
  }
  public static Quiz createQuizB() {
    return Quiz.builder()
        .id(2L)
        .author(TestUtil.createUserB())
        .name("Quiz B")
        .description("Description B")
        .questions(List.of(createQuizQuestionA(), createQuizQuestionB()))
        .isOpen(true)
        .build();
  }


}
