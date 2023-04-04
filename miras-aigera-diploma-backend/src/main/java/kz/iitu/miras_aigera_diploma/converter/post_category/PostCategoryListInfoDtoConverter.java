package kz.iitu.miras_aigera_diploma.converter.post_category;

import kz.iitu.miras_aigera_diploma.model.dto.post_category.PostCategoryListInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.PostCategory;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class PostCategoryListInfoDtoConverter extends
    AbstractConverter<PostCategory, PostCategoryListInfoDto> {

  @Override
  public void fill(PostCategory source, PostCategoryListInfoDto target) {
    target.setId(source.getId());
    target.setName(source.getName());
    target.setCode(source.getCode());
  }
}
