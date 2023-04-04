package kz.iitu.miras_aigera_diploma.model.dto.city;

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
@Schema(description = "DTO for cities list info")
public class CityListInfoDto {

  @Schema(description = "City id", example = "1")
  Long id;

  @Schema(description = "City name", example = "Almaty")
  String name;
}
