package kz.iitu.miras_aigera_diploma.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "DTO for user credential info")
public class UserMeInfoDto {

  @Schema(description = "Username", example = "020501550659")
  String username;

  @Schema(description = "User role", example = "ROLE_USER")
  String role;

  @Schema(description = "User firstname", example = "Miras")
  String firstName;

  @Schema(description = "User district", example = "Bostandyksky")
  String district;

  @Schema(description = "User city", example = "Almaty")
  String city;
}
