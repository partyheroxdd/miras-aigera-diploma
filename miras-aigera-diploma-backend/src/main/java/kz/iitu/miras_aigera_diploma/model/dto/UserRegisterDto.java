package kz.iitu.miras_aigera_diploma.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

  private String username;
  private String phone;
  private String fullName;
  private String address;
  private String location;
  private String position;
  private String password;
  private String[] roles;
}
