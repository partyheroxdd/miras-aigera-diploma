package kz.iitu.miras_aigera_diploma.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import kz.iitu.miras_aigera_diploma.exceptions.DiplomaCoreException;
import kz.iitu.miras_aigera_diploma.service.CDNMinioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class CDNMinioServiceImpl implements CDNMinioService {

  private static final String NULL_FILE_ERROR_MESSAGE = "Пожалуйста, прикрепите файл.";
  private static final String UPLOADING_FILE_SIZE_LOG_MESSAGE = "Размер загружаемого файла %s.";
  private static final String CONVERT_FILE_ERROR_LOG_MESSAGE = "Произошла ошибка при конвертации файла %s.";
  private static final String UPLOAD_FILE_ERROR_LOG_MESSAGE = "uploadFileAndGetBucketKey - произошла ошибка при загрузке файла %s CDN.";
  private static final String UPLOAD_FILE_ERROR_CODE = "UPLOAD_FILE_TO_OBJECT_STORE_ERROR";
  private static final String UPLOAD_FILE_LOG_MESSAGE = "Загрузить файл %s.";
  private static final String PREFIX_KEY = "images/";

  @Autowired
  @Qualifier("amazonMinioS3Client")
  private AmazonS3 amazonS3Client;

  @Value("${cloud.minio.host}")
  private String host;

  @Value("${cloud.minio.bucket}")
  private String bucket;

  @Override
  public String uploadFile(MultipartFile multipartFile) {
    if (multipartFile == null) {
      throw new IllegalArgumentException(NULL_FILE_ERROR_MESSAGE);
    }
    log.info(String.format(UPLOADING_FILE_SIZE_LOG_MESSAGE, multipartFile.getSize()));
    try {
      File file = convertMultiPartFileToFile(multipartFile);
      String bucketKey = uploadFileToS3BucketAndGetKey(file);
      file.delete();
      return String.format(host) + "/" + bucket + "/" + bucketKey;
    } catch (AmazonServiceException e) {
      log.error(String.format(UPLOAD_FILE_ERROR_LOG_MESSAGE, multipartFile.getOriginalFilename()),
          e);
      throw new DiplomaCoreException(HttpStatus.INTERNAL_SERVER_ERROR, UPLOAD_FILE_ERROR_CODE,
          multipartFile.getOriginalFilename());
    }
  }

  @Override
  public void delete(String path) {
    String buckets = String.format(host) + "/" + bucket;
    if (path.startsWith(buckets)) {
      path = path.substring(buckets.length() + 1);
    }
    amazonS3Client.deleteObject(bucket, path);
  }

  private File convertMultiPartFileToFile(MultipartFile multipartFile) {
    File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      outputStream.write(multipartFile.getBytes());
    } catch (IOException e) {
      log.error(CONVERT_FILE_ERROR_LOG_MESSAGE, e);
    }
    return file;
  }

  private String uploadFileToS3BucketAndGetKey(File file) {
    String bucketKey = PREFIX_KEY + LocalDateTime.now() + "_" + file.getName();
    PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, bucketKey, file);
    log.info(String.format(UPLOAD_FILE_LOG_MESSAGE, file.getName()));
    amazonS3Client.putObject(putObjectRequest);
    return bucketKey;
  }
}
