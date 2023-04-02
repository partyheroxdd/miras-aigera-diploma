package kz.iitu.miras_aigera_diploma.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "DTO for create posts")
public class PostCreateDto {

  @Schema(description = "Post publish city", example = "Almaty")
  private String city;

  @Schema(description = "Post publish city", example = "Almalinsky")
  private String district;

  @Schema(description = "Post publish datetime", type = "string", pattern = "dd-MM-yyyy HH:mm", example = "10-05-2021 13:40")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
  private LocalDateTime dateTime;

  @Schema(description = "Post category name", example = "lost thing", required = true)
  private String postCategory;

  @Schema(description = "Post publish description", example = "White red color")
  private String description;

  @Schema(description = "Post publish details", example = "On the Tole bi street")
  private String details;

}
