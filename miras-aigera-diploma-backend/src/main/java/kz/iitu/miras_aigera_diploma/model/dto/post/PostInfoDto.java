package kz.iitu.miras_aigera_diploma.model.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
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

  @Schema(description = "Post id", example = "1")
  Long id;

  @Schema(description = "Post number", example = "№ 015621890210")
  String postNumber;

  @Schema(description = "Post status and handle dateTime", example = "Approved 29-03-2022 16:00")
  String statusAndHandleDateTime;

  @Schema(description = "Post city", example = "Almaty")
  String city;

  @Schema(description = "Post district", example = "Bostandyksky")
  String district;

  @Schema(description = "Post incident time", type = "string", pattern = "dd-MM-yyyy HH:mm", example = "25-03-2022 13:10")
  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
  LocalDateTime incidentTime;

  @Schema(description = "Post category", example = "loss of property")
  String category;

  @Schema(description = "Post description", example = "Lost dog in the Orbita area, looking for the third day")
  String description;

  @Schema(description = "Post additional info", example = "With black cloth and red collar")
  String additionalInfo;

  @Schema(description = "Post published citizen username", example = "020501550659")
  String username;

  @Schema(description = "Post image url")
  String imageUrl;
}
