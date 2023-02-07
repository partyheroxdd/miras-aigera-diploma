package kz.iitu.miras_aigera_diploma.converter.post;

import kz.iitu.miras_aigera_diploma.model.dto.post.PostUpdateDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.repository.PostCategoryRepository;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostUpdateDtoConverter extends AbstractConverter<PostUpdateDto, Post> {

  private final PostCategoryRepository postCategoryRepository;

  @Override
  public void fill(PostUpdateDto source, Post target) {
    target.setId(source.getId());
    target.setCity(source.getCity());
    target.setDistrict(source.getDistrict());
    target.setDateTime(source.getDateTime());
    target.setPostCategory(postCategoryRepository.findById(source.getPostCategoryId()).get());
    target.setDescription(source.getDescription());
    target.setDetails(source.getDetails());
  }

}
