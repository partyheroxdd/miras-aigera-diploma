package kz.iitu.miras_aigera_diploma.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
@Schema(description = "DTO for change password")
public class UserChangePasswordDto {

  @Schema(description = "User's username", example = "020501550651")
  @NotBlank(message = "Username field required")
  String username;

  @Schema(description = "User's password", example = "kekkekkek")
  @NotBlank(message = "Password field required")
  @Size(min = 8, max = 20, message = "Password length must be minimum 8, maximum 20")
  String password;

  @Schema(description = "User's retyped password", example = "kekkekkek")
  @NotBlank(message = "Repeat password field required")
  @Size(min = 8, max = 20, message = "Password length must be minimum 8, maximum 20")
  String newPassword;
}
