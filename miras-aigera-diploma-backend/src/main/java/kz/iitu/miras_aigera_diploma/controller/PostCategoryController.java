package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.post_category.PostCategoryInfoDto;
import kz.iitu.miras_aigera_diploma.service.PostCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post_categories")
@Tag(name = "Post categories API", description = "Methods to works with post categories")
public class PostCategoryController {

  private final PostCategoryService postCategoryService;

  @GetMapping()
  @Operation(summary = "Method to get all post categories")
  public ResponseEntity<List<PostCategoryInfoDto>> getAllPostCategories() {
    return ResponseEntity.ok(postCategoryService.findAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Method to get post category by id")
  public ResponseEntity<PostCategoryInfoDto> getPostCategoryById(
      @Parameter(description = "Post category id", example = "1") @PathVariable Long id) {
    return ResponseEntity.ok(postCategoryService.findById(id));
  }
}
