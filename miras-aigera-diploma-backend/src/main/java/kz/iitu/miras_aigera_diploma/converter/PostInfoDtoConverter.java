package kz.iitu.miras_aigera_diploma.converter;

import kz.iitu.miras_aigera_diploma.model.dto.PostCreateDto;
import kz.iitu.miras_aigera_diploma.model.dto.PostInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class PostInfoDtoConverter extends AbstractConverter<Post, PostInfoDto> {

  @Override
  public void fill(Post source, PostInfoDto target) {
    target.setId(source.getId());
    target.setCity(source.getCity());
    target.setDistrict(source.getDistrict());
    target.setPostCategory(source.getPostCategory().getName());
    target.setDateTime(source.getDateTime());
    target.setDescription(source.getDescription());
    target.setDetails(source.getDetails());
    target.setApproved(source.getApproved());
  }
}
