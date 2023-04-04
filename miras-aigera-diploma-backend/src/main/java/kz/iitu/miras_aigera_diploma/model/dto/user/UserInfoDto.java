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
@Schema(description = "DTO for user info")
public class UserInfoDto {

  @Schema(description = "User firstname", example = "Miras")
  String firstname;

  @Schema(description = "User lastname", example = "Madiyev")
  String lastname;

  @Schema(description = "User lastname", example = "Serikovich")
  String midname;

  @Schema(description = "User's username-iin", example = "020501550659")
  String iin;

  @Schema(description = "User's phone number", example = "+77005553535")
  String phoneNumber;

  @Schema(description = "User's city", example = "Almaty")
  String city;

  @Schema(description = "User's address", example = "Tole bi 40, 232")
  private String address;

}
