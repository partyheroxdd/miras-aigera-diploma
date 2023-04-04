package kz.iitu.miras_aigera_diploma.converter.post;

import kz.iitu.miras_aigera_diploma.model.dto.post.PostListInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.service.UserService;
import kz.iitu.miras_aigera_diploma.util.JwtUtil;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostListInfoDtoConverter extends AbstractConverter<Post, PostListInfoDto> {

  UserService userService;

  @Override
  public void fill(Post source, PostListInfoDto target) {
    String role = JwtUtil.getRole();
    target.setId(source.getId());
    target.setPostNumber(source.getNumber());
    target.setCategory(source.getCategory().getName());
    target.setIncidentTime(source.getDateTime());
    if ("ROLE_USER".equals(role) || "ROLE_ADMIN".equals(role)) {
      target.setStatus(source.getStatus().getName());
    }
  }
}
