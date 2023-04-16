package kz.iitu.miras_aigera_diploma.converter.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostListInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.City;
import kz.iitu.miras_aigera_diploma.model.entity.District;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.model.entity.PostCategory;
import kz.iitu.miras_aigera_diploma.model.entity.PostStatus;
import kz.iitu.miras_aigera_diploma.util.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostListInfoDtoConverterTest {

  @InjectMocks
  PostListInfoDtoConverter postListInfoDtoConverter;

  @DisplayName("Test converter from Post to PostListInfoDto")
  @Test
  void testFill() {
    Post post = new Post();
    post.setId(1L);
    post.setNumber("P0001");
    PostCategory category = new PostCategory();
    category.setName("Crime");
    post.setCategory(category);
    post.setDateTime(LocalDateTime.now());
    PostStatus postStatus = new PostStatus();
    postStatus.setName("Pending");
    post.setStatus(postStatus);
    District district = new District();
    district.setName("Almaty");
    post.setDistrict(district);
    City city = new City();
    city.setName("Almaty");
    post.setCity(city);

    Mockito.mockStatic(JwtUtil.class);

    when(JwtUtil.getRole()).thenReturn("ROLE_USER");

    PostListInfoDto expected = new PostListInfoDto();
    expected.setId(1L);
    expected.setPostNumber("P0001");
    expected.setCategory("Crime");
    expected.setIncidentTime(post.getDateTime());
    expected.setStatus("Pending");
    expected.setDistrict("Almaty");
    expected.setCity("Almaty");

    PostListInfoDto actual = new PostListInfoDto();
    postListInfoDtoConverter.fill(post, actual);

    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getPostNumber(), actual.getPostNumber());
    assertEquals(expected.getCategory(), actual.getCategory());
    assertEquals(expected.getIncidentTime(), actual.getIncidentTime());
    assertEquals(expected.getStatus(), actual.getStatus());
    assertEquals(expected.getDistrict(), actual.getDistrict());
    assertEquals(expected.getCity(), actual.getCity());
  }
}
