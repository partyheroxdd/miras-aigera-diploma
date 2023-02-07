package kz.iitu.miras_aigera_diploma.converter.post;

import kz.iitu.miras_aigera_diploma.converter.post_category.PostCategoryInfoDtoConverter;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostInfoDtoConverter extends AbstractConverter<Post, PostInfoDto> {

  private final PostCategoryInfoDtoConverter postCategoryInfoDtoConverter;

  @Override
  public void fill(Post source, PostInfoDto target) {
    target.setId(source.getId());
    target.setCity(source.getCity());
    target.setDistrict(source.getDistrict());
    target.setPostCategory(postCategoryInfoDtoConverter.convert(source.getPostCategory()));
    target.setDateTime(source.getDateTime());
    target.setDescription(source.getDescription());
    target.setDetails(source.getDetails());
    target.setApproved(source.getApproved());
  }
}
