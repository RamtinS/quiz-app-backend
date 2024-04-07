
package edu.ntnu.idatt2105.quizapp.services.quiz;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizPreviewDto;
import edu.ntnu.idatt2105.quizapp.exception.auth.UnauthorizedOperationException;
import edu.ntnu.idatt2105.quizapp.mapper.QuizMapper;
import edu.ntnu.idatt2105.quizapp.model.user.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizAttemptRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizRepository;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import edu.ntnu.idatt2105.quizapp.util.quiz.QuizModelTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the QuizService class. The tests are done with Mockito.
 * The tests are testing the functionality of the methods in the QuizService class.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

  @InjectMocks
  private QuizService quizService;
  @Mock
  QuizRepository quizRepository;
  @Mock
  QuizAttemptRepository quizAttemptRepository;
  @Mock
  UserRepository userRepository;
  @Mock
  QuizMapper quizMapper;

  Principal principal;

  User user;

  Quiz quiz;

  @BeforeEach
  void setUp() {
    user = TestUtil.createUserA();
    quiz = QuizModelTestUtil.createQuizA();
    principal = () -> user.getUsername();
  }

  @Test
  void QuizService_CreateQuiz_ReturnQuizCreationResponseDTO() {
    //Arrange
    quiz.setId(1L);
    when(userRepository.findUserByUsernameIgnoreCase(user.getUsername())).thenReturn(Optional.ofNullable(user));
    when(quizMapper.mapToQuiz(TestUtil.createQuizCreationRequestDtoA(), user)).thenReturn(quiz);
    when(quizRepository.save(quiz)).thenReturn(quiz);

    //Act
    quizService.createQuiz(TestUtil.createQuizCreationRequestDtoA(), principal);
    //Assert
    assertNotNull(quiz);
  }

  @Test
  void QuizService_getAllQuizPreviewsForUserPaginated_ReturnQuizzes() {
    //Arrange
    when(quizRepository.findAllByAuthorUsername(principal.getName(), null)).thenReturn(List.of(quiz));
    int expected = 1;

    //Act
    List<QuizPreviewDto>  actual = quizService.getAllQuizPreviewsForUserPaginated(principal, null);

    //Assert
    assertEquals(expected, actual.size());
  }

  @Test
  void QuizService_GetAllPublicQuizPreviewsForUserPaginated_ReturnPublicQuizzesByAuthor() {
    //Arrange
    when(quizRepository.findAllByAuthorUsernameAndIsOpen(user.getUsername(), null, true)).thenReturn(List.of(quiz));
    int expected = 1;

    //Act
    List<QuizPreviewDto> actual = quizService.getAllPublicQuizPreviewsForUserPaginated(user.getUsername(), null);

    //Assert
    assertEquals(expected, actual.size());
  }

  @Test
  void QuizService_BrowsePublicQuizzesPaginated_ReturnAllPublicQuizzes() {
    //Arrange
    when(quizRepository.findAllByIsOpen(true, null)).thenReturn(List.of(quiz));
    int expected = 1;

    //Act
    List<QuizPreviewDto> actual = quizService.browsePublicQuizzesPaginated(null);

    //Assert
    assertEquals(expected, actual.size());
  }

  @Test
  void QuizService_GetQuizById_ReturnSpecifiedQuiz() {

    //Arrange
    Quiz expected = quiz;
    when(quizRepository.findQuizById(quiz.getId())).thenReturn(Optional.of(expected));
    when(quizMapper.mapToQuizDto(expected)).thenReturn((QuizDto.builder()
            .description(expected.getDescription())
            .name(expected.getName())
            .categoryDescription(expected.getCategory().getDescription())
            .build()));


    //Act
    QuizDto actual = quizService.getQuizById(principal, quiz.getId());

    //Assert
    assertEquals(expected.getDescription(), actual.getDescription());
    assertEquals(expected.getName(), actual.getName());
    assertEquals(expected.getCategory().getDescription(), actual.getCategoryDescription());
  }

  @Test
  void QuizService_GetQuizByIdWhichAuthorDoesNotOwn_ReturnException() {
    //Arrange
    Quiz expected = quiz;
    expected.setIsOpen(false);
    Principal testPrincipal = () -> "NotAuthor";

    when(quizRepository.findQuizById(quiz.getId())).thenReturn(Optional.of(expected));

    //Act
    assertThrows(UnauthorizedOperationException.class, () -> quizService.getQuizById(testPrincipal, quiz.getId()));
  }


  @Test
  void QuizService_GetQuizBySearchParametersBothTrue_ReturnListOfQuizPreview() {
    //Arrange
    Quiz expected = quiz;
    boolean searchByCategory = true;
    boolean searchByTags = true;

    when(quizRepository.findQuizByCategoryDescriptionAndTagsDescriptionContainingIgnoreCaseAndIsOpen("Maths", "Maths", true, null)).thenReturn(List.of(quiz));
    when(quizMapper.mapToQuizPreviewDto(expected)).thenReturn(QuizPreviewDto.builder()
            .description(expected.getDescription())
            .title(expected.getName())
            .open(expected.getIsOpen())
            .build());

    int expectedSize = 1;

    //Act
    List<QuizPreviewDto> actual = quizService.getQuizBySearchParameters("Maths", searchByCategory, searchByTags, null);

    //Assert
    assertEquals(expectedSize, actual.size());
  }

  @Test
  void QuizService_GetQuizBySearchParametersTagsTrue_ReturnListOfQuizPreview() {
    //Arrange
    Quiz expected = quiz;
    boolean searchByCategory = false;
    boolean searchByTags = true;

    when(quizRepository.findQuizByTagsDescriptionContainingIgnoreCaseAndIsOpen("Maths", true, null)).thenReturn(List.of(quiz));
    when(quizMapper.mapToQuizPreviewDto(expected)).thenReturn(QuizPreviewDto.builder()
            .description(expected.getDescription())
            .title(expected.getName())
            .open(expected.getIsOpen())
            .build());

    int expectedSize = 1;

    //Act
    List<QuizPreviewDto> actual = quizService.getQuizBySearchParameters("Maths", searchByCategory, searchByTags, null);

    //Assert
    assertEquals(expectedSize, actual.size());
  }

  @Test
  void QuizService_GetQuizBySearchParametersCategoryTrue_ReturnListOfQuizPreview() {
    //Arrange
    Quiz expected = quiz;
    boolean searchByCategory = true;
    boolean searchByTags = false;

    when(quizRepository.findQuizByCategoryDescriptionContainingIgnoreCaseAndIsOpen("Maths", true, null)).thenReturn(List.of(quiz));
    when(quizMapper.mapToQuizPreviewDto(expected)).thenReturn(QuizPreviewDto.builder()
            .description(expected.getDescription())
            .title(expected.getName())
            .open(expected.getIsOpen())
            .build());

    int expectedSize = 1;

    //Act
    List<QuizPreviewDto> actual = quizService.getQuizBySearchParameters("Maths", searchByCategory, searchByTags, null);

    //Assert
    assertEquals(expectedSize, actual.size());
  }

  @Test
  void QuizService_GetQuizBySearchParametersBothFalse_ReturnListOfQuizPreview() {
    //Arrange
    Quiz expected = quiz;
    boolean searchByCategory = false;
    boolean searchByTags = false;

    when(quizRepository.findAllByNameContainingIgnoreCaseAndIsOpenOrderByName("Maths", true, null)).thenReturn(List.of(quiz));
    when(quizMapper.mapToQuizPreviewDto(expected)).thenReturn(QuizPreviewDto.builder()
            .description(expected.getDescription())
            .title(expected.getName())
            .open(expected.getIsOpen())
            .build());

    int expectedSize = 1;

    //Act
    List<QuizPreviewDto> actual = quizService.getQuizBySearchParameters("Maths", searchByCategory, searchByTags, null);

    //Assert
    assertEquals(expectedSize, actual.size());
  }

  @Test
  void QuizService_UpdateQuiz_ReturnListOfQuizPreview() {
    //Arrange
    Quiz expected = quiz;
    when(userRepository.findUserByUsernameIgnoreCase(principal.getName())).thenReturn(Optional.of(user));
    when(quizRepository.findQuizById(quiz.getId())).thenReturn(Optional.of(expected));
    when(quizMapper.mapToQuiz(TestUtil.createQuizCreationRequestDtoA(), user)).thenReturn(quiz);
    when(quizRepository.save(quiz)).thenReturn(quiz);

    //Act
    quizService.updateQuiz(principal, quiz.getId(), TestUtil.createQuizCreationRequestDtoA());
    //Assert
    assertNotNull(quiz);
  }

  @Test
  void deleteQuiz() {
    //Arrange
    Quiz expected = quiz;
    when(quizRepository.findQuizById(quiz.getId())).thenReturn(Optional.of(expected));
    when(userRepository.findUserByUsernameIgnoreCase(principal.getName())).thenReturn(Optional.of(user));


    assertDoesNotThrow(() -> quizService.deleteQuiz(principal, quiz.getId()));
  }
}
