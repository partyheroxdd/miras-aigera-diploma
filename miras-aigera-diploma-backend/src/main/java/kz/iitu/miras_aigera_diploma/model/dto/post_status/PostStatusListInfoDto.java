package kz.iitu.miras_aigera_diploma.model.dto.post_status;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.iitu.miras_aigera_diploma.model.enums.StatusCode;
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
@Schema(description = "DTO for post statuses list info")
public class PostStatusListInfoDto {

  @Schema(description = "Post status id", example = "1")
  Long id;

  @Schema(description = "Post status name", example = "Approved")
  String name;

  @Schema(description = "Post status code", example = "APPROVED")
  StatusCode code;
}
