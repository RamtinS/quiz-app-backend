
package edu.ntnu.idatt2105.quizapp.mapper;

import edu.ntnu.idatt2105.quizapp.dto.quiz.TagDto;
import edu.ntnu.idatt2105.quizapp.model.quiz.Tag;
import edu.ntnu.idatt2105.quizapp.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagMapperTest {

  TagMapper tagMapper;

  @BeforeEach
  void setUp() {
    tagMapper = new TagMapper();
  }

  @Test
  void TagMapper_MapToTagDto_ReturnTagDto() {
    // Arrange
    Tag tag = TestUtil.createTagA();

    // Act
    TagDto tagDto = tagMapper.mapToTagDto(tag);

    // Assert
    assertEquals(tag.getDescription(), tagDto.getDescription());
  }

  @Test
  void mapToTag() {
    // Arrange
    TagDto tagDto = TagDto.builder()
            .description("description")
            .build();

    // Act
    Tag tag = tagMapper.mapToTag(tagDto);

    // Assert
    assertEquals(tagDto.getDescription(), tag.getDescription());
  }
}
