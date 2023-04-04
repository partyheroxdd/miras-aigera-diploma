package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.post_status.PostStatusListInfoDto;
import kz.iitu.miras_aigera_diploma.service.PostStatusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post_statuses")
@Tag(name = "Post statuses API", description = "Methods to work with post statuses")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostStatusController {

  PostStatusService postStatusService;

  @GetMapping()
  @Operation(summary = "Method to get all post statuses")
  public ResponseEntity<List<PostStatusListInfoDto>> getAllPostStatuses() {
    return ResponseEntity.ok(postStatusService.findAll());
  }

}
