package edu.ntnu.idatt2105.quizapp.controller.quiz;

import edu.ntnu.idatt2105.quizapp.model.quiz.Category;
import edu.ntnu.idatt2105.quizapp.services.quiz.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("/api/v1/category-management")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

  @NonNull
  CategoryService categoryService;

  /**
   * Get all possible categories stored in the database
   *
   * @return a list of all possible categories
   */
  @Operation(summary = "Get all possible categories")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved all categories",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = List.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @GetMapping("/categories")
  public ResponseEntity<List<String>> getAllCategories(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {

    List<String> receivedCategories =
        categoryService.getAllPossibleCategories(Pageable.ofSize(size).withPage(page))
            .stream()
            .map(Category::getDescription)
            .toList();
    return new ResponseEntity<>(receivedCategories, HttpStatus.OK);
  }
}
