package kz.iitu.miras_aigera_diploma.model.dto.user;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
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
@Schema(description = "DTO for user login")
public class UserLoginDto {

  @Schema(description = "User's username", example = "020501550659")
  @NotBlank(message = "Username field required")
  String username;

  @Schema(description = "User's password", example = "kekkekkek")
  @NotBlank(message = "Password field required")
  String password;
}
