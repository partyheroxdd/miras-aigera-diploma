package kz.iitu.miras_aigera_diploma.model.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "DTO for posts search")
public class PostSearchDto {

  @Schema(description = "Posts incident date from filter", type = "string", pattern = "dd-MM-yyyy", example = "25-03-2022")
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  Date dateFrom;

  @Schema(description = "Posts incident date to filter", type = "string", pattern = "dd-MM-yyyy", example = "25-04-2022")
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  Date dateTo;

  @Schema(description = "District filter", example = "5")
  Long districtId;

  @Schema(description = "City filter", example = "16")
  Long cityId;

  @Schema(description = "Post category filter", example = "1")
  Long categoryId;

  @Schema(description = "Post status filter", example = "1")
  Long statusId;

  @Schema(description = "Query search for category, post number, district, city")
  String query;
}
