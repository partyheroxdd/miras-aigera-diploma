package kz.iitu.miras_aigera_diploma.model.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import kz.iitu.miras_aigera_diploma.model.enums.CategoryCode;
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
@Schema(description = "DTO for create posts")
public class PostCreateDto {

  @Schema(description = "City name", example = "Almaty")
  @NotBlank(message = "City field required")
  String city;

  @Schema(description = "District name", example = "Bostandyksky")
  String district;

  @Schema(description = "Incident time", type = "string", pattern = "dd-MM-yyyy HH:mm", example = "25-03-2022 13:10")
  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
  @NotNull(message = "Incident time field required")
  LocalDateTime incidentTime;

  @Schema(description = "Post category code", example = "LOSS_PROPERTY")
  @NotNull(message = "Post category field required")
  CategoryCode category;

  @Schema(description = "Description", example = "Lost dog in the Orbita area, looking for the third day")
  @NotBlank(message = "Description field required")
  @Size(min = 20, max = 100, message = "Description of the incident must be at least 20, no more 100 characters")
  String description;

  @Schema(description = "Additional info", example = "With black cloth and red collar")
  String additionalInfo;
}
