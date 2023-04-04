package kz.iitu.miras_aigera_diploma.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@Schema(description = "Dto for user registration")
public class UserRegisterDto {

  @Schema(description = "User firstname", example = "Miras")
  @NotBlank(message = "User firstname field required")
  @Size(max = 50, message = "User firstname must be no more 50 characters")
  String firstname;

  @Schema(description = "User lastname", example = "Madiyev")
  @NotBlank(message = "User lastname field required")
  @Size(max = 50, message = "User lastname must be no more 50 characters")
  String lastname;

  @Schema(description = "User midname", example = "Serikovich")
  String midname;

  @Schema(description = "User username-iin", example = "020501550659")
  @NotBlank(message = "Username field required")
  String username;

  @Schema(description = "User phone number", example = "+77005553535")
  @Size(min = 12, max = 12, message = "Phone Number Should Be Valid")
  String phoneNumber;

  @Schema(description = "User city", example = "Almaty")
  @NotBlank(message = "User city field required")
  String city;

  @Schema(description = "User district - only for ROLE_DISTRICT_POLICEMAN", example = "Bostandyksky")
  String district;

  @Schema(description = "User's address", example = "Tole bi 259")
  String address;

  @Schema(description = "User's password", example = "kekkekkek")
  @Size(min = 8, max = 40, message = "Password length must be minimum 8, maximum 40")
  String password;

  @Schema(description = "Role of user", example = "ROLE_USER")
  @NotNull(message = "Role field required")
  String role;

}
