package edu.ntnu.idatt2105.quizapp.services.quiz;

import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizDto;
import edu.ntnu.idatt2105.quizapp.dto.quiz.QuizPreviewDTO;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationRequestDTO;
import edu.ntnu.idatt2105.quizapp.dto.quiz.creation.QuizCreationResponseDTO;
import edu.ntnu.idatt2105.quizapp.mapper.QuizMapper;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.model.quiz.Quiz;
import edu.ntnu.idatt2105.quizapp.model.quiz.QuizAttempt;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizAttemptRepository;
import edu.ntnu.idatt2105.quizapp.repositories.quiz.QuizRepository;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
  public QuizCreationResponseDTO createQuiz(QuizCreationRequestDTO quizCreationDTO,
                                            Principal principal)
      throws UsernameNotFoundException {

    Optional<User> user = userRepository.findUserByUsernameIgnoreCase(principal.getName());

    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }

    Quiz createdQuiz = quizMapper.mapToQuiz(quizCreationDTO, user.get());

    Quiz savedQuiz = quizRepository.save(createdQuiz);

    return QuizCreationResponseDTO.builder()
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
  public List<QuizPreviewDTO> getAllQuizPreviewsForUserPaginated(Principal principal,
                                                                 Pageable pageable) {


    return quizRepository.findAllByAuthorUsername(principal.getName(), pageable)
        .stream()
        .map(quizMapper::mapToQuizPreviewDTO)
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
  public List<QuizPreviewDTO> getAllPublicQuizPreviewsForUserPaginated(String username,
                                                                       Pageable pageable) {
    return quizRepository.findAllByAuthorUsernameAndIsOpen(username, pageable, true)
        .stream()
        .map(quizMapper::mapToQuizPreviewDTO)
        .toList();
  }

  /**
   * Creates a paginated list of previews for all publicly available quizzes.
   *
   * @param pageable The pageable object used to specify the page number and size.
   * @return A list of quiz previews based on the pageable.
   */
  public List<QuizPreviewDTO> browsePublicQuizzesPaginated(Pageable pageable) {
    return quizRepository.findAllByIsOpen(true, pageable)
        .stream()
        .map(quizMapper::mapToQuizPreviewDTO)
        .toList();
  }

  /**
   * Gets a quiz by its ID if the quiz is public or the principal is the author.
   *
   * @param principal The principal of the logged-in user.
   * @param id        The ID of the quiz.
   * @return A QuizDTO of the quiz.
   * @throws NoSuchElementException   If the quiz is not found.
   * @throws IllegalArgumentException If the principal has no access to the quiz.
   */
  public QuizDto getQuizById(Principal principal, long id)
      throws NoSuchElementException, IllegalArgumentException {

    Quiz quiz = quizRepository.findQuizById(id).orElseThrow();

    if (quiz.getIsOpen() || quiz.getAuthor().getUsername().equals(principal.getName())) {
      return quizMapper.mapToQuizDTO(quiz);
    } else {
      throw new IllegalArgumentException("Principal has no access to this quiz.");
    }

  }


  /**
   * Gets a public quiz by its name.
   *
   * @param search the term to search the quiz with.
   * @param pageable The pageable object used to specify the page number and size.
   * @return a list of quizPreviews.
   */
  public List<QuizPreviewDTO> getQuizBySearchParameters(
          String search,
          Boolean searchByCategory,
          Boolean searchByTags,
          Pageable pageable) {

    if (searchByCategory && searchByTags) {
      return quizRepository.findQuizByCategoryDescriptionAndTagsDescriptionContainingIgnoreCaseAndIsOpen(search, search, true, pageable).stream()
              .map(quizMapper::mapToQuizPreviewDTO)
              .toList();
    }

    if (searchByCategory) {
      return quizRepository.findQuizByCategoryDescriptionContainingIgnoreCaseAndIsOpen(search, true, pageable).stream()
              .map(quizMapper::mapToQuizPreviewDTO)
              .toList();
    }

    if (searchByTags) {
      return quizRepository.findQuizByTagsDescriptionContainingIgnoreCaseAndIsOpen(search, true, pageable).stream()
              .map(quizMapper::mapToQuizPreviewDTO)
              .toList();

    } else {
      return quizRepository.findAllByNameContainingIgnoreCaseAndIsOpenOrderByName(search, true, pageable).stream()
              .map(quizMapper::mapToQuizPreviewDTO)
              .toList();
    }
    }

  public QuizCreationResponseDTO updateQuiz(Principal principal, long id,
                                            QuizCreationRequestDTO quizCreationDTO) {

    Optional<User> user = userRepository.findUserByUsernameIgnoreCase(principal.getName());
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }

    Quiz quiz = quizRepository.findQuizById(id).orElseThrow();
    if (!quiz.getAuthor().getUsername().equals(principal.getName())) {
      throw new IllegalArgumentException("User does not have permission to edit this quiz.");
    }

    quiz = quizMapper.mapToQuiz(quizCreationDTO, user.get());

    Quiz savedQuiz = quizRepository.save(quiz);


    Quiz originalQuiz = quizRepository.findQuizById(id).orElseThrow();


    deleteQuizAndCorrespondingAttempts(id, originalQuiz);

    return QuizCreationResponseDTO.builder()
        .quizId(savedQuiz.getId())
        .build();
  }

  private void deleteQuizAndCorrespondingAttempts(long id, Quiz originalQuiz) {
    List<QuizAttempt> result = quizAttemptRepository.findQuizAttemptByQuiz_Id(id);
    quizAttemptRepository.deleteAll(result);
    quizRepository.delete(originalQuiz);
  }


  public void deleteQuiz(Principal principal, long quizId) {

    Optional<User> user = userRepository.findUserByUsernameIgnoreCase(principal.getName());
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }

    Quiz quiz = quizRepository.findQuizById(quizId).orElseThrow();
    if (!quiz.getAuthor().getUsername().equals(principal.getName())) {
      throw new IllegalArgumentException("User does not have permission to edit this quiz.");
    }

    deleteQuizAndCorrespondingAttempts(quizId, quiz);
  }
}
