package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.TagDto;
import edu.ntnu.idatt2105.quizapp.model.quiz.Tag;
import org.springframework.stereotype.Component;

/**
  * Mapper class for mapping Tag objects to TagDTO objects and vice versa.
  *
  * @author Jeffrey Yaw Annor Tabiri
  * @version 1.0
  * @since 2024-03-31
 */
@Component
public class TagMapper {
  /**
   * Maps a Tag object to an TagDto object.
   *
   * @param tag The tag object to map.
   * @return The mapped TagDto object.
   */
  public TagDto mapToTagDto(Tag tag) {
    return TagDto.builder()
            .description(tag.getDescription())
            .build();
  }

  /**
   * Maps a TagDto object to an tag object.
   *
   * @param tagDto The tagDto object to map.
   * @return The mapped tag object.
   */
  public Tag mapToTag(TagDto tagDto) {
    return Tag.builder()
            .description(tagDto.getDescription())
            .build();
  }

}
