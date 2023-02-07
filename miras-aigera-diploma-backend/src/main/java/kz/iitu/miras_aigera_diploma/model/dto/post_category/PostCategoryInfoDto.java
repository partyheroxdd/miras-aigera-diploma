package kz.iitu.miras_aigera_diploma.model.dto.post_category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "DTO for posts category info")
public class PostCategoryInfoDto {

  @Schema(description = "Post category id", example = "1")
  private Long id;

  @Schema(description = "Post category name", example = "lost thing")
  private String name;
}
