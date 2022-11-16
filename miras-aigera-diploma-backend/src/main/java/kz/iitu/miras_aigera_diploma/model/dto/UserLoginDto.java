package kz.iitu.miras_aigera_diploma.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "DTO for user login")
public class UserLoginDto {

  @Schema(description = "User's username", example = "020501550659")
  @NotBlank
  private String username;

  @Schema(description = "User's password", example = "kekkekkek")
  @NotBlank
  private String password;
}
