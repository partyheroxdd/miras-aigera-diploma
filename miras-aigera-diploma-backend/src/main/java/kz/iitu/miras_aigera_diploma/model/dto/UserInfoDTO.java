package kz.iitu.miras_aigera_diploma.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserInfoDTO {
  private Long id;
  private String username;
  private String fullName;
  private String address;
  private String phone;
}
