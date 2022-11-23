package kz.iitu.miras_aigera_diploma.converter;

import kz.iitu.miras_aigera_diploma.model.dto.PostCreateDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.repository.PostCategoryRepository;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostCreateDtoConverter extends AbstractConverter<PostCreateDto, Post> {

  private final PostCategoryRepository postCategoryRepository;
  @Override
  public void fill(PostCreateDto source, Post target) {
    target.setCity(source.getCity());
    target.setDistrict(source.getDistrict());
    target.setPostCategory(postCategoryRepository.findByName(source.getPostCategory()));
    target.setDateTime(source.getDateTime());
    target.setDescription(source.getDescription());
    target.setDetails(source.getDetails());
    target.setApproved(false);
  }
}
