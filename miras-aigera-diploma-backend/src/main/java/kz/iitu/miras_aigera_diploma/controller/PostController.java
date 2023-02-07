package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostCreateDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostUpdateDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.service.PostService;
import kz.iitu.miras_aigera_diploma.util.must_have.dto_util.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@Tag(name = "Posts API", description = "Methods for work with posts")
public class PostController {

  private final PostService postService;

  @GetMapping("/{id}")
  @Operation(summary = "Method to get post with Id")
  public ResponseEntity<PostInfoDto> getPost(
      @Parameter(description = "Post id", example = "1", required = true) @PathVariable final Long id) {
    return ResponseEntity.ok(postService.getPost(id));
  }

  @PostMapping
  @Operation(summary = "Save new post")
  public ResponseEntity<PostCreateDto> save(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request Body of PostCreateDto") @RequestBody PostCreateDto postCreateDto) {
    return ResponseEntity.ok(postService.savePost(postCreateDto));
  }

  @PutMapping()
  @Operation(summary = "Method to update post")
  public ResponseEntity<PostUpdateDto> updatePost(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request Body of PostUpdateDto") @RequestBody PostUpdateDto postUpdateDto) {
    return ResponseEntity.ok(postService.updatePost(postUpdateDto));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Method to delete post with Id")
  public ResponseEntity<?> delete(
      @Parameter(description = "Post id", example = "1", required = true) @PathVariable Long id) {
    postService.deletePost(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping
  @Operation(summary = "Method to get all paginated sorted posts")
  public ResponseEntity<PageDTO<PostInfoDto>> getAllPosts(
      @Parameter(description = "City filter", example = "Almaty") @RequestParam(required = false) String
          city,
      @Parameter(description = "Approved filter", example = "false") @RequestParam(required = false) Boolean
          approved,
      @Parameter(description = "Query search", example = "IPhone") @RequestParam(required = false) String
          query,
      @PageableDefault(
          sort = {Post.Fields.id},
          direction = Sort.Direction.ASC) Pageable pageable) {
    return ResponseEntity.ok(postService.getAllPosts(city, approved, query, pageable));
  }

  @PostMapping("/approve/{id}")
  @Operation(summary = "Approve post")
  public ResponseEntity<?> approve(
      @Parameter(description = "Post id", example = "1", required = true) @PathVariable Long id,
      @Parameter(description = "approved status", example = "false", required = true) @RequestParam boolean approved) {
    postService.approvePost(id, approved);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
