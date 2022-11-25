package kz.iitu.miras_aigera_diploma.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Schema(description = "DTO for user me info")
public class UserMeInfoDto {

  @Schema(description = "User's id", example = "1")
  private Long id;

  @Schema(description = "User's username", example = "020501550659")
  private String username;

  @Schema(description = "User's role name", example = "ROLE_USER")
  private String roleName;
}
