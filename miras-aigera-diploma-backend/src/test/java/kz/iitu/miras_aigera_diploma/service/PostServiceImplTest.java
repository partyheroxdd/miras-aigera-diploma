package kz.iitu.miras_aigera_diploma.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import kz.iitu.miras_aigera_diploma.exceptions.DiplomaCoreException;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostChangeStatusDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.model.enums.StatusCode;
import kz.iitu.miras_aigera_diploma.repository.PostRepository;
import kz.iitu.miras_aigera_diploma.repository.PostStatusRepository;
import kz.iitu.miras_aigera_diploma.service.impl.PostServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

  @Mock
  PostRepository postRepository;

  @Mock
  PostStatusRepository postStatusRepository;

  @InjectMocks
  PostServiceImpl postService;


  @DisplayName("Test change status of post")
  @Test
  void testChangeStatus() {
    Post post = new Post();
    post.setId(1L);

    PostChangeStatusDto postChangeStatusDto = new PostChangeStatusDto();
    postChangeStatusDto.setId(1L);
    postChangeStatusDto.setStatusCode(StatusCode.APPROVED);

    when(postRepository.findById(1L)).thenReturn(Optional.of(post));

    when(postStatusRepository.findByCode(StatusCode.APPROVED)).thenReturn(null);

    postService.changeStatus(postChangeStatusDto);

    verify(postRepository, times(1)).save(post);
    verify(postStatusRepository, times(1)).findByCode(StatusCode.APPROVED);
  }

  @DisplayName("Test change status post not found")
  @Test
  void testChangeStatusPostNotFound() {
    Long postId = 1L;

    when(postRepository.findById(postId)).thenReturn(Optional.empty());

    assertThrows(DiplomaCoreException.class, () ->
        postService.changeStatus(new PostChangeStatusDto(postId, StatusCode.APPROVED)));

  }
}
