package kz.iitu.miras_aigera_diploma.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidateResetCodeDTO {
  private String resetCode;
}