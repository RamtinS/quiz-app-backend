package edu.ntnu.idatt2105.quizapp.services.quiz;

import edu.ntnu.idatt2105.quizapp.model.quiz.Tag;
import edu.ntnu.idatt2105.quizapp.repositories.TagRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Tag entities.
 */
@Service
@Slf4j
@AllArgsConstructor
public class TagService {
  @NonNull
  TagRepository tagRepository;

  public Page<Tag> getAllPossibleTags(Pageable pageable) {
    return tagRepository.findAll(pageable);
  }
}
