package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostChangeStatusDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostCreateDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostListInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostSearchDto;
import kz.iitu.miras_aigera_diploma.util.must_have.dto_util.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {

  void save(PostCreateDto postCreateDto, MultipartFile file);

  PostInfoDto findById(Long id);

  List<PostListInfoDto> findAll(PostSearchDto postSearchDto);

  void changeStatus(PostChangeStatusDto postChangeStatusDto);
}
