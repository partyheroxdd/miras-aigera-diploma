package kz.iitu.miras_aigera_diploma.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Dto for user update")
public class UserUpdateDto {

  @Schema(description = "User firstname", example = "Miras")
  @Size(max = 50, message = "User firstname must be no more 50 characters")
  String firstname;

  @Schema(description = "User lastname", example = "Madiyev")
  @Size(max = 50, message = "User lastname must be no more 50 characters")
  String lastname;

  @Schema(description = "User midname", example = "Serikovich")
  String midname;

  @Schema(description = "User phone number", example = "+77005553535")
  @Size(min = 12, max = 12, message = "Phone Number Should Be Valid")
  String phoneNumber;

  @Schema(description = "User city", example = "Almaty")
  String city;

  @Schema(description = "User's address", example = "Tole bi 259")
  String address;
}
