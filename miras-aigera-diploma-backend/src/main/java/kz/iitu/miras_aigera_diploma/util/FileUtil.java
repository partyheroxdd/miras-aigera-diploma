package kz.iitu.miras_aigera_diploma.util;

import java.util.Objects;
import java.util.Set;
import kz.iitu.miras_aigera_diploma.exceptions.DiplomaCoreException;
import kz.iitu.miras_aigera_diploma.model.constants.ApiMessages;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Sets;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

@UtilityClass
@Slf4j
public class FileUtil {

  static Set<String> CONTENT_TYPES = Sets.newHashSet(
      "image/jpg",
      "image/jpeg");

  public void checkContentType(MultipartFile multipartFile) {
    if (Objects.nonNull(multipartFile)) {
      String contentType = multipartFile.getContentType();
      if (Objects.nonNull(contentType)) {
        log.info("Content type of file {}", contentType);
        if (!isSupportedContentType(contentType)) {
          throw new DiplomaCoreException(HttpStatus.BAD_REQUEST, ApiMessages.FILE_TYPE_NOT_SUPPORT,
              "File type not supported");
        }
      }
    }
  }

  private boolean isSupportedContentType(String contentType) {
    return CONTENT_TYPES.contains(contentType);
  }
}
