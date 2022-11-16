package kz.iitu.miras_aigera_diploma.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "DTO for change password request")
public class UserChangePasswordDTO {

  @Schema(description = "User's username", example = "020501550651")
  @NotBlank
  private String username;

  @Schema(description = "User's password", example = "kekkekkek")
  @Size(min = 8, max = 40, message = "Password length must be minimum 8, maximum 40")
  private String password;

  @Schema(description = "User's retyped password", example = "kekkekkek")
  @Size(min = 8, max = 40, message = "Password length must be minimum 8, maximum 40")
  private String reTypedPassword;
}
