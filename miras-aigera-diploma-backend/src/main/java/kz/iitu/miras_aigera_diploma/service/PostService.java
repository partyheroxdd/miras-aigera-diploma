package kz.iitu.miras_aigera_diploma.service;

import kz.iitu.miras_aigera_diploma.model.dto.post.PostChangeStatusDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostCreateDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostListInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostSearchDto;
import kz.iitu.miras_aigera_diploma.util.must_have.dto_util.PageDTO;
import org.springframework.data.domain.Pageable;

public interface PostService {

  void save(PostCreateDto postCreateDto);

  PostInfoDto findById(Long id);

  PageDTO<PostListInfoDto> findAll(PostSearchDto postSearchDto, Pageable pageable);

  void changeStatus(PostChangeStatusDto postChangeStatusDto);
}
