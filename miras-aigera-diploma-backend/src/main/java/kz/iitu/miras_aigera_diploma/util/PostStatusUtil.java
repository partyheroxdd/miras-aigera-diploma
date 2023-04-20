package kz.iitu.miras_aigera_diploma.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PostStatusUtil {

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

  public String getFormattedStatusAndHandleDateTime(Post post) {
    String status = post.getStatus().getName();

    LocalDateTime handleDateTime = post.getUpdatedAt().toLocalDateTime();

    return status + " " + handleDateTime.format(formatter);
  }
}
