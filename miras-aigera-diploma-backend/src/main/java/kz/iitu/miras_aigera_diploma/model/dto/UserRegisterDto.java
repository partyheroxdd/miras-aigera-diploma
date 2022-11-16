package kz.iitu.miras_aigera_diploma.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto for user registration request")
public class UserRegisterDto {

  @Schema(description = "User's username", example = "020501550659")
  @NotBlank
  private String username;

  @Schema(description = "User's phone number", example = "+77005553535")
  @Size(min = 12, max = 12, message = "Phone Number Should Be Valid")
  private String phone;

  @Schema(description = "User's full name", example = "Serikov Serik Serikovich")
  @NotBlank
  private String fullName;

  @Schema(description = "User's address", example = "Tole bi 259")
  @NotBlank
  private String address;

  @Schema(description = "User's location, if is policeman", example = "Almalinsky")
  private String location;

  @Schema(description = "User's position, if is policeman", example = "Police sergeant")
  private String position;

  @Schema(description = "User's password", example = "kekkekkek")
  @Size(min = 8, max = 40, message = "Password length must be minimum 8, maximum 40")
  private String password;

  @Schema(description = "Roles of user", example = "[ROLE_POLICEMAN]")
  private String[] roles;

}
