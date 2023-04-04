package kz.iitu.miras_aigera_diploma.model.dto.district;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO for districts list info")
public class DistrictListInfoDto {

  @Schema(description = "District id", example = "1")
  Long id;

  @Schema(description = "District name", example = "Bostandyksky")
  String name;
}