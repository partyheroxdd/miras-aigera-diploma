package kz.iitu.miras_aigera_diploma.model.dto.post_category;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO for post categories list info")
public class PostCategoryListInfoDto {

  @Schema(description = "Post category id", example = "1")
  Long id;

  @Schema(description = "Post category name", example = "loss of property")
  String name;

  @Schema(description = "Post category code", example = "LOSS_PROPERTY")
  CategoryCode code;
}
