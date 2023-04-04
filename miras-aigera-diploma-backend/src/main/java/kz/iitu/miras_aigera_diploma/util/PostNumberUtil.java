package kz.iitu.miras_aigera_diploma.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PostNumberUtil {

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  public String generatePostNumber(String username) {
    LocalDateTime now = LocalDateTime.now();
    String formattedDate = now.format(formatter);
    return "№" + " " + formattedDate + username.substring(6);
  }
}
