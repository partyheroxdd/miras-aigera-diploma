package kz.iitu.miras_aigera_diploma.model.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import kz.iitu.miras_aigera_diploma.model.dto.post_category.PostCategoryInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "DTO for posts info")
public class PostInfoDto {

  @Schema(description = "Post publish id", example = "1")
  private Long id;

  @Schema(description = "Post publish city", example = "Almaty")
  private String city;

  @Schema(description = "Post publish city", example = "Almalinsky")
  private String district;

  @Schema(description = "Post publish datetime", example = "10.05.2021 13:40")
  @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
  private LocalDateTime dateTime;

  @Schema(description = "Post category DTO")
  private PostCategoryInfoDto postCategory;

  @Schema(description = "Post publish description", example = "White red color")
  private String description;

  @Schema(description = "Post publish details", example = "On the Tole bi street")
  private String details;

  @Schema(description = "Post approve detail", example = "false")
  private Boolean approved;

}
