package kz.iitu.miras_aigera_diploma.converter.post_status;

import kz.iitu.miras_aigera_diploma.model.dto.post_status.PostStatusListInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.PostStatus;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class PostStatusListInfoDtoConverter extends
    AbstractConverter<PostStatus, PostStatusListInfoDto> {

  @Override
  public void fill(PostStatus source, PostStatusListInfoDto target) {
    target.setId(source.getId());
    target.setName(source.getName());
    target.setCode(source.getCode());
  }
}

