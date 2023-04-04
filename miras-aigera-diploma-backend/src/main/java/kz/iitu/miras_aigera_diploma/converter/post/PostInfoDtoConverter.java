package kz.iitu.miras_aigera_diploma.converter.post;

import java.util.Objects;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.util.PostStatusAndDateTimeUtil;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class PostInfoDtoConverter extends AbstractConverter<Post, PostInfoDto> {

  @Override
  public void fill(Post source, PostInfoDto target) {
    target.setId(source.getId());
    target.setPostNumber(source.getNumber());
    target.setStatusAndHandleDateTime(
        PostStatusAndDateTimeUtil.getFormattedStatusAndHandleDateTime(source));
    target.setCity(source.getCity().getName());
    if (Objects.nonNull(source.getDistrict())) {
      target.setDistrict(source.getDistrict().getName());
    }
    target.setIncidentTime(source.getDateTime());
    target.setCategory(source.getCategory().getName());
    target.setDescription(source.getDescription());
    if (Objects.nonNull(source.getAdditionalInfo())) {
      target.setAdditionalInfo(source.getAdditionalInfo());
    }
    target.setUsername(source.getUser().getUsername());
  }
}
