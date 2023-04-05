package kz.iitu.miras_aigera_diploma.service;

import org.springframework.web.multipart.MultipartFile;

public interface CDNMinioService {

  String uploadFile(MultipartFile file);

  void delete(String url);
}
