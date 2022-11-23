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
@Schema(description = "DTO for user info")
public class UserInfoDto {

  @Schema(description = "User's id", example = "1")
  private Long id;

  @Schema(description = "User's username", example = "020501550659")
  private String username;

  @Schema(description = "User's full name", example = "Serikov Serik")
  private String fullName;

  @Schema(description = "User's address", example = "Tole bi 40, 232")
  private String address;

  @Schema(description = "User's phone", example = "+77005553535")
  private String phone;
}
