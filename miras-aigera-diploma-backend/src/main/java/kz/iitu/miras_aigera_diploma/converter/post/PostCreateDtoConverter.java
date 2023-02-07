package kz.iitu.miras_aigera_diploma.converter.post;

import kz.iitu.miras_aigera_diploma.model.dto.post.PostCreateDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.repository.PostCategoryRepository;
import kz.iitu.miras_aigera_diploma.repository.PostRepository;
import kz.iitu.miras_aigera_diploma.service.PostCategoryService;
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
    target.setPostCategory(postCategoryRepository.findById(source.getPostCategoryId()).get());
    target.setDateTime(source.getDateTime());
    target.setDescription(source.getDescription());
    target.setDetails(source.getDetails());
    target.setApproved(false);
  }
}
