package edu.ntnu.idatt2105.quizapp.repositories;

import edu.ntnu.idatt2105.quizapp.model.quiz.Tag;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Tag entities.
 *
 * @author Tobias Oftedal
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

  @NonNull Page<Tag> findAll(@NonNull Pageable pageable);
}
