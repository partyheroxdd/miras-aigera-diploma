package kz.iitu.miras_aigera_diploma.service;

import kz.iitu.miras_aigera_diploma.model.dto.post.PostCreateDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostUpdateDto;
import kz.iitu.miras_aigera_diploma.util.must_have.dto_util.PageDTO;
import org.springframework.data.domain.Pageable;

public interface PostService {

  PostCreateDto savePost(PostCreateDto postCreateDto);

  PostInfoDto getPost(Long id);

  PostUpdateDto updatePost(PostUpdateDto postUpdateDto);

  void deletePost(Long id);

  void approvePost(Long id, Boolean approved);

  PageDTO<PostInfoDto> getAllPosts(String city, Boolean approved, String query, Pageable pageable);
}
