package kz.iitu.miras_aigera_diploma.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LostPeoplePostDto {

  private String city;
  private String district;
  private LocalDateTime dateTime;
  private String fullName;
  private LocalDate birthday;
  private String identityData;
  private String placeOfResidence;
  private String typeOfActivity;
  private String imageUrl;
}
