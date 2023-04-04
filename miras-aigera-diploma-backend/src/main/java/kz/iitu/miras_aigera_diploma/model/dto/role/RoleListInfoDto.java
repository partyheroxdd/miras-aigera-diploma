package kz.iitu.miras_aigera_diploma.model.dto.role;

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
@Schema(description = "DTO for roles list info")
public class RoleListInfoDto {

  @Schema(description = "Role id", example = "1")
  Long id;

  @Schema(description = "Role name", example = "Administrator")
  String name;
}
