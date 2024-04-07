package edu.ntnu.idatt2105.quizapp.services.quiz;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizPreviewDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationRequestDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationResponseDto;
import edu.ntnu.idatt2105.quizapp.exception.auth.UnauthorizedOperationException;
import edu.ntnu.idatt2105.quizapp.exception.quiz.QuizNotFoundException;
import edu.ntnu.idatt2105.quizapp.mapper.QuizMapper;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizAttemptRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizRepository;
import java.security.Principal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class for quiz-related operations. Such as creating and fetching quizzes.
 *
 * @author Tobias Oftedal
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class QuizService {

  @NonNull
  private final QuizRepository quizRepository;
  @NonNull
  private final QuizAttemptRepository quizAttemptRepository;
  @NonNull
  private final UserRepository userRepository;
  @NonNull
  private final QuizMapper quizMapper;

  /**
   * Creates a quiz from a quiz creation DTO for the logged-in user.
   *
   * @param quizCreationDTO The DTO containing the quiz information.
   * @param principal       The principal of the logged-in user.
   * @return The response DTO containing the ID of the created quiz.
   * @throws UsernameNotFoundException If the user is not found.
   */
  public QuizCreationResponseDto createQuiz(QuizCreationRequestDto quizCreationDTO,
                                            Principal principal) throws UsernameNotFoundException {

    String username = principal.getName();

    User user = userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() ->
            new UsernameNotFoundException("User with username " + username + " not found"));

    Quiz createdQuiz = quizMapper.mapToQuiz(quizCreationDTO, user);

    Quiz savedQuiz = quizRepository.save(createdQuiz);

    return QuizCreationResponseDto.builder()
        .quizId(savedQuiz.getId())
        .build();
  }

  /**
   * Creates a paginated list of previews for all quizzes (both public and private)
   * where the author is the logged-in user.
   *
   * @param principal The principal of the logged-in user.
   * @param pageable  The pageable object containing the page number and size.
   * @return A list of quiz previews based on the pageable.
   */
  public List<QuizPreviewDto> getAllQuizPreviewsForUserPaginated(Principal principal,
                                                                 Pageable pageable) {

    return quizRepository.findAllByAuthorUsername(principal.getName(), pageable)
        .stream()
        .map(quizMapper::mapToQuizPreviewDto)
        .toList();
  }

  /**
   * Creates a paginated list of previews for all public quizzes where the author has the given
   * username.
   *
   * @param username The username of the author of the quiz.
   * @param pageable The pageable object used to specify the page number and size.
   * @return A list of quiz public quiz previews of the user based on the pageable.
   */
  public List<QuizPreviewDto> getAllPublicQuizPreviewsForUserPaginated(String username,
                                                                       Pageable pageable) {
    return quizRepository.findAllByAuthorUsernameAndIsOpen(username, pageable, true)
        .stream()
        .map(quizMapper::mapToQuizPreviewDto)
        .toList();
  }

  /**
   * Creates a paginated list of previews for all publicly available quizzes.
   *
   * @param pageable The pageable object used to specify the page number and size.
   * @return A list of quiz previews based on the pageable.
   */
  public List<QuizPreviewDto> browsePublicQuizzesPaginated(Pageable pageable) {
    return quizRepository.findAllByIsOpen(true, pageable)
        .stream()
        .map(quizMapper::mapToQuizPreviewDto)
        .toList();
  }

  /**
   * Retrieves a quiz by its ID if the quiz is public or the principal is the author.
   *
   * @param principal The principal of the logged-in user.
   * @param id The ID of the quiz.
   * @return A QuizDTO of the quiz.
   * @throws QuizNotFoundException If the quiz is not found.
   * @throws UnauthorizedOperationException If the principal has no access to the quiz.
   */
  public QuizDto getQuizById(Principal principal, long id)
      throws QuizNotFoundException, UnauthorizedOperationException {

    Quiz quiz = quizRepository.findQuizById(id).orElseThrow(QuizNotFoundException::new);

    if (quiz.getIsOpen() || quiz.getAuthor().getUsername().equals(principal.getName())) {
      return quizMapper.mapToQuizDto(quiz);
    } else {
      throw new UnauthorizedOperationException("Principal has no access to this quiz.");
    }

  }

  /**
   * Retrieves a public quiz by its name.
   *
   * @param search the term to search the quiz with.
   * @param pageable The pageable object used to specify the page number and size.
   * @return a list of quizPreviews.
   */
  public List<QuizPreviewDto> getQuizBySearchParameters(
          String search,
          Boolean searchByCategory,
          Boolean searchByTags,
          Pageable pageable) {

    if (searchByCategory && searchByTags) {
      return quizRepository
              .findQuizByCategoryDescriptionAndTagsDescriptionContainingIgnoreCaseAndIsOpen(
                      search, search, true, pageable).stream()
              .map(quizMapper::mapToQuizPreviewDto)
              .toList();
    }

    if (searchByCategory) {
      return quizRepository
              .findQuizByCategoryDescriptionContainingIgnoreCaseAndIsOpen(search, true, pageable)
              .stream()
              .map(quizMapper::mapToQuizPreviewDto)
              .toList();
    }

    if (searchByTags) {
      return quizRepository
              .findQuizByTagsDescriptionContainingIgnoreCaseAndIsOpen(search, true, pageable)
              .stream()
              .map(quizMapper::mapToQuizPreviewDto)
              .toList();

    } else {
      return quizRepository
              .findAllByNameContainingIgnoreCaseAndIsOpenOrderByName(search, true, pageable)
              .stream()
              .map(quizMapper::mapToQuizPreviewDto)
              .toList();
    }
  }

  /**
   * The method updates a quiz with the provided information.
   *
   * @param principal The principal of the logged-in user.
   * @param id The ID of the quiz to update.
   * @param quizCreationDTO The DTO containing the updated quiz information.
   * @return The response DTO containing the ID of the updated quiz.
   * @throws UsernameNotFoundException If the user is not found.
   * @throws UnauthorizedOperationException If the user does not have permission to delete the quiz.
   * @throws QuizNotFoundException If the quiz is not found.
   */
  public QuizCreationResponseDto updateQuiz(Principal principal, long id,
                                            QuizCreationRequestDto quizCreationDTO)
          throws UsernameNotFoundException, UnauthorizedOperationException, QuizNotFoundException {

    String username = principal.getName();

    User user = userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() ->
            new UsernameNotFoundException("User with username " + username + " not found"));

    Quiz quiz = quizRepository.findQuizById(id).orElseThrow();
    if (!quiz.getAuthor().getUsername().equals(username)) {
      throw new UnauthorizedOperationException("User does not have permission to edit this quiz.");
    }

    quiz = quizMapper.mapToQuiz(quizCreationDTO, user);

    Quiz savedQuiz = quizRepository.save(quiz);

    Quiz originalQuiz = quizRepository.findQuizById(id).orElseThrow(QuizNotFoundException::new);

    deleteQuizAndCorrespondingAttempts(id, originalQuiz);

    return QuizCreationResponseDto.builder()
        .quizId(savedQuiz.getId())
        .build();
  }

  /**
   * The method deletes a quiz.
   *
   * @param principal The principal of the logged-in user.
   * @param quizId  The ID of the quiz to be deleted.
   * @throws UsernameNotFoundException If the user is not found.
   * @throws QuizNotFoundException If the quiz is not found.
   * @throws UnauthorizedOperationException If the user does not have permission to delete the quiz.
   */
  public void deleteQuiz(Principal principal, long quizId) throws UsernameNotFoundException,
          QuizNotFoundException, UnauthorizedOperationException {

    String username = principal.getName();

    userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() ->
            new UsernameNotFoundException("User with username" + username + "not found."));

    Quiz quiz = quizRepository.findQuizById(quizId).orElseThrow(QuizNotFoundException::new);

    if (!quiz.getAuthor().getUsername().equals(username)) {
      throw new UnauthorizedOperationException("User does not have permission to edit this quiz.");
    }

    deleteQuizAndCorrespondingAttempts(quizId, quiz);
  }

  /**
   * Deletes a quiz and its corresponding attempts from the database.
   *
   * @param id The ID of the quiz to be deleted.
   * @param originalQuiz The original quiz object to delete attempts from.
   */
  private void deleteQuizAndCorrespondingAttempts(long id, Quiz originalQuiz) {
    List<QuizAttempt> result = quizAttemptRepository.findQuizAttemptByQuiz_Id(id);
    quizAttemptRepository.deleteAll(result);
    quizRepository.delete(originalQuiz);
  }
}
