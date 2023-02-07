package kz.iitu.miras_aigera_diploma.converter.post_category;

import kz.iitu.miras_aigera_diploma.model.dto.post_category.PostCategoryInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.PostCategory;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class PostCategoryInfoDtoConverter extends
    AbstractConverter<PostCategory, PostCategoryInfoDto> {

  @Override
  public void fill(PostCategory source, PostCategoryInfoDto target) {
    target.setId(source.getId());
    target.setName(source.getName());
  }
}
