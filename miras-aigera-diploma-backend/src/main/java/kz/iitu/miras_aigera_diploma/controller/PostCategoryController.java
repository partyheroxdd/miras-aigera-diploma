package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.post_category.PostCategoryListInfoDto;
import kz.iitu.miras_aigera_diploma.service.PostCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post_categories")
@Tag(name = "Post categories API", description = "Methods to work with post categories")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostCategoryController {

  PostCategoryService postCategoryService;

  @GetMapping()
  @Operation(summary = "Method to get all post categories")
  public ResponseEntity<List<PostCategoryListInfoDto>> getAllPostCategories() {
    return ResponseEntity.ok(postCategoryService.findAll());
  }

}
