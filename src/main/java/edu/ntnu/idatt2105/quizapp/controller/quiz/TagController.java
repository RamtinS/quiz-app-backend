package edu.ntnu.idatt2105.quizapp.controller.quiz;

import edu.ntnu.idatt2105.quizapp.dto.quiz.TagDto;
import edu.ntnu.idatt2105.quizapp.mapper.TagMapper;
import edu.ntnu.idatt2105.quizapp.services.quiz.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tag-management")
@CrossOrigin(origins = "http://localhost:3000")
public class TagController {

  @NonNull
  TagService tagService;

  @NonNull
  TagMapper tagMapper;

  /**
   * Get all possible tags stored in the database
   *
   * @return a list of all possible tags
   */
  @Operation(summary = "Get all possible tags")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved all tags"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/tags")
  public ResponseEntity<List<TagDto>> getAllTags(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    List<TagDto> receivedTags = tagService.getAllPossibleTags(Pageable.ofSize(size).withPage(page))
        .stream()
        .map(tagMapper::mapToTagDto)
        .toList();
    return new ResponseEntity<>(receivedTags, HttpStatus.OK);
  }


}
