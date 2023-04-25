package kz.iitu.miras_aigera_diploma.model.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import kz.iitu.miras_aigera_diploma.model.enums.CategoryCode;
import kz.iitu.miras_aigera_diploma.model.enums.StatusCode;
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

  @Schema(description = "District filter", example = "Bostandyksky")
  String district;

  @Schema(description = "City filter", example = "Almaty")
  String city;

  @Schema(description = "Post category filter", example = "LOSS_PROPERTY")
  CategoryCode category;

  @Schema(description = "Post status filter", example = "APPROVED")
  StatusCode status;

  @Schema(description = "Query search for post number")
  String query;
}
