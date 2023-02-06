package kz.iitu.miras_aigera_diploma.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "DTO for role info")
public class RoleDto {

  @Schema(description = "Role's id", example = "1")
  private Long id;

  @Schema(description = "Role's name", example = "ROLE_ADMIN")
  private String name;
}
