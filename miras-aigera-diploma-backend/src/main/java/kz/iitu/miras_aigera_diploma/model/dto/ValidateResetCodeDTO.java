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
@Schema(description = "DTO for validate reset code")
public class ValidateResetCodeDTO {

  @Schema(description = "User's reset code", example = "2534")
  @NotBlank
  private String resetCode;
}
