package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.PostDto;
import kz.iitu.miras_aigera_diploma.util.must_have.dto_util.PageDTO;
import org.springframework.data.domain.Pageable;

public interface PostService {

  PostDto savePost(PostDto postDto);

  PostDto getPost(Long id);

  void deletePost(Long id);

  List<PostDto> getAllApprovedPosts();

  void approvePost(Long id, boolean approved);

  PageDTO<PostDto> getAllPosts(String city, String query, Pageable pageable);
}
