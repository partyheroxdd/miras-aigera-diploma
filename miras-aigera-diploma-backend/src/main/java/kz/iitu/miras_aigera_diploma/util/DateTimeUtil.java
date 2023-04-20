package kz.iitu.miras_aigera_diploma.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateTimeUtil {

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

  public LocalDateTime toLocalDateTime(String dateTime) {
    return LocalDateTime.parse(dateTime, formatter);
  }
}
