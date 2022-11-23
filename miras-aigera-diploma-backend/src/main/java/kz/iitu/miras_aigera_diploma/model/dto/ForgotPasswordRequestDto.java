package kz.iitu.miras_aigera_diploma.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for forgot password request")
public class ForgotPasswordRequestDto {

  @Schema(description = "User's email", example = "miras.madiev15@gmail.com")
  @NotBlank
  private String email;
}
