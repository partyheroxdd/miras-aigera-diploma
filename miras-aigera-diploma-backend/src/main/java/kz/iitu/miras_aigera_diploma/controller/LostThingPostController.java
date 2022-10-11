package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.LostThingPostDto;
import kz.iitu.miras_aigera_diploma.model.entity.LostThingPost;
import kz.iitu.miras_aigera_diploma.service.LostThingPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lost-things-posts")
@Tag(name = "LostThings", description = "Lost Things Posts API")
public class LostThingPostController {

  private final LostThingPostService lostThingPostService;

  @GetMapping("/{id}")
  @Operation(summary = "Method to get post with Id")
  public ResponseEntity<LostThingPostDto> getLostThingsPost(
      @Parameter(description = "Post id") @PathVariable final Long id) {
    return ResponseEntity.ok(lostThingPostService.getLostThingPost(id));
  }

  @PostMapping
  @Operation(summary = "Save new post")
  public ResponseEntity<LostThingPostDto> save(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request Body of Post") @RequestBody LostThingPostDto lostThingPostDto) {
    return ResponseEntity.ok(lostThingPostService.saveLostThingPost(lostThingPostDto));
  }

  @DeleteMapping(value = "/{id}")
  @Operation(summary = "Method to delete post with Id")
  public ResponseEntity<?> delete(@Parameter(description = "Post id") @PathVariable Long id) {
    lostThingPostService.deleteLostThingPost(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/all")
  @Operation(summary = "Method to get all posts")
  public ResponseEntity<List<LostThingPost>> getAll() {
    return ResponseEntity.ok(lostThingPostService.getAllLostThingPosts());
  }

  @PostMapping(value = "/approve/{id}")
  @Operation(summary = "Approve post")
  public ResponseEntity<?> approve(@Parameter(description = "Post id") @PathVariable Long id,
      @Parameter(description = "approved status") @RequestParam boolean approved) {
    lostThingPostService.approveLostThingsPost(id, approved);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
