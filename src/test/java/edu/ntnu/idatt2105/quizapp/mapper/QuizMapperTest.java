
package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.MultipleChoiceQuestionDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuestionDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizPreviewDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.TagDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationRequestDto;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizMapperTest {

  @InjectMocks
  QuizMapper quizMapper;
  @Mock
  UserMapper userMapper;

  @Mock
  QuestionMapper questionMapper;

  @Mock
  TagMapper tagMapper;

  Quiz quiz;

  @BeforeEach
  void setUp() {
    quiz = QuizModelTestUtil.createQuizA();
    quiz.setTags(List.of(TestUtil.createTagA()));
  }

  @Test
  void QuizMapper_MapToQuizDTO_ReturnQuizDTO() {

    //Act
    QuizDto quizDto = quizMapper.mapToQuizDto(quiz);

    //Assert
    assertNotNull(quizDto);
  }

  @Test
  void QuizMapper_MapToQuizPreviewDTO_QuizPreviewDTO() {
    //Act
    QuizPreviewDto quizDto = quizMapper.mapToQuizPreviewDto(quiz);

    //Assert
    assertNotNull(quizDto);
  }

  @Test
  void QuizMapper_MapToQuiz_ReturnQuiz() {

    TagDto tagDto = TagDto.builder()
            .description("Tag A")

            .build();

    QuestionDto multipleChoiceQuestionDto = MultipleChoiceQuestionDto.builder()
            .questionText("Question A")
            .answers(List.of())
            .build();

    //Arrange
    QuizCreationRequestDto quizCreationRequestDTO = QuizCreationRequestDto.builder()
            .title("Quiz A")
            .description("Description A")
            .categoryDescription("Category A")
            .open(true)
            .questions(List.of(multipleChoiceQuestionDto))
            .tags(List.of(tagDto)).build();


    when(questionMapper.mapToQuestion(any(QuestionDto.class))).thenReturn(QuizModelTestUtil.createQuizQuestionA());
    when(tagMapper.mapToTag(any(TagDto.class))).thenReturn(TestUtil.createTagA());

    String expectedQuizName = quizCreationRequestDTO.getTitle();
    String expectedQuizDescription = quizCreationRequestDTO.getDescription();
    String expectedCategoryDescription = quizCreationRequestDTO.getCategoryDescription();
    boolean expectedIsOpen = quizCreationRequestDTO.isOpen();

    //Act
    Quiz actual = quizMapper.mapToQuiz(quizCreationRequestDTO, TestUtil.createUserA());

    //Assert
    assertEquals(expectedQuizName, actual.getName());
  }
}
