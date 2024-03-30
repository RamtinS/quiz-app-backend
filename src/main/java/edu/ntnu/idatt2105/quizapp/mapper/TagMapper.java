package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.AnswerDTO;
import edu.ntnu.idatt2105.quizapp.dto.quiz.TagDto;
import edu.ntnu.idatt2105.quizapp.model.quiz.Answer;
import edu.ntnu.idatt2105.quizapp.model.quiz.Tag;

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
