package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostChangeStatusDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostCreateDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostListInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostSearchDto;
import kz.iitu.miras_aigera_diploma.model.enums.CategoryCode;
import kz.iitu.miras_aigera_diploma.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@Tag(name = "Posts API", description = "Methods for work with posts")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostController {

  PostService postService;

  @GetMapping("/{id}")
  @Operation(summary = "Method to get post with Id")
  public ResponseEntity<PostInfoDto> getPost(
      @Parameter(description = "Post id", example = "1", required = true) @PathVariable Long id) {
    return ResponseEntity.ok(postService.findById(id));
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(summary = "Method to save new post")
  public ResponseEntity<String> save(
      @RequestParam(value = "city") String city,
      @RequestParam(value = "district", required = false) String district,
      @RequestParam(value = "incidentTime") String incidentTime,
      @RequestParam(value = "category") CategoryCode category,
      @RequestParam(value = "description") String description,
      @RequestParam(value = "additionalInfo", required = false) String additionalInfo,
      @Parameter(description = "Post image", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
      @RequestPart(value = "image", required = false)
          MultipartFile file) {
    PostCreateDto postCreateDto = PostCreateDto.builder()
        .city(city)
        .district(district)
        .incidentTime(incidentTime)
        .category(category)
        .description(description)
        .additionalInfo(additionalInfo)
        .build();
    postService.save(postCreateDto, file);
    return ResponseEntity.ok("Post created successfully");
  }

  @PatchMapping("/status")
  @Operation(summary = "Method to change status of posts")
  public ResponseEntity<String> changeStatusOfPost(
      @RequestBody PostChangeStatusDto postChangeStatusDto) {
    postService.changeStatus(postChangeStatusDto);
    return ResponseEntity.ok("Post status changed successfully");
  }

  @GetMapping
  @Operation(summary = "Method to get all posts")
  public ResponseEntity<List<PostListInfoDto>> getAllPosts(
      @ParameterObject PostSearchDto postSearchDto) {
    return ResponseEntity.ok(postService.findAll(postSearchDto));
  }

}
