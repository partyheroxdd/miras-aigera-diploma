package kz.iitu.miras_aigera_diploma.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LostThingPostDto {

  private String city;
  private String district;
  @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
  private LocalDateTime dateTime;
  private String category;
  private String description;
  private String details;
}
