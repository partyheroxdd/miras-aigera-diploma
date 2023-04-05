package kz.iitu.miras_aigera_diploma.model.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "DTO for posts list info")
public class PostListInfoDto {

  @Schema(description = "Post id", example = "1")
  Long id;

  @Schema(description = "Post number", example = "№ 015621890210")
  String postNumber;

  @Schema(description = "Post category", example = "loss of property")
  String category;

  @Schema(description = "Post status", example = "Approved")
  String status;

  @Schema(description = "Post incident time", type = "string", pattern = "dd-MM-yyyy HH:mm", example = "25-03-2022 13:10")
  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
  LocalDateTime incidentTime;

  @Schema(description = "Post city", example = "Almaty")
  String city;

  @Schema(description = "Post district", example = "Bostandyksky")
  String district;
}
