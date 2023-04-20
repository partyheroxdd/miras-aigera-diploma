package kz.iitu.miras_aigera_diploma.converter.post;

import java.util.Objects;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostCreateDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.model.enums.StatusCode;
import kz.iitu.miras_aigera_diploma.repository.CityRepository;
import kz.iitu.miras_aigera_diploma.repository.DistrictRepository;
import kz.iitu.miras_aigera_diploma.repository.PostCategoryRepository;
import kz.iitu.miras_aigera_diploma.repository.PostStatusRepository;
import kz.iitu.miras_aigera_diploma.service.UserService;
import kz.iitu.miras_aigera_diploma.util.DateTimeUtil;
import kz.iitu.miras_aigera_diploma.util.JwtUtil;
import kz.iitu.miras_aigera_diploma.util.PostNumberUtil;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostCreateDtoConverter extends AbstractConverter<PostCreateDto, Post> {

  CityRepository cityRepository;
  DistrictRepository districtRepository;
  PostCategoryRepository postCategoryRepository;
  PostStatusRepository postStatusRepository;

  UserService userService;

  @Override
  public void fill(PostCreateDto source, Post target) {
    String username = JwtUtil.getUsername();
    target.setNumber(PostNumberUtil.generatePostNumber(username));
    target.setCity(cityRepository.findByName(source.getCity()));
    if (Objects.nonNull(source.getDistrict())) {
      target.setDistrict(districtRepository.findByName(source.getDistrict()));
    }
    target.setStatus(postStatusRepository.findByCode(StatusCode.UNDER_CONSIDERATION));
    target.setDateTime(DateTimeUtil.toLocalDateTime(source.getIncidentTime()));
    target.setCategory(postCategoryRepository.findByCode(source.getCategory()));
    target.setUser(userService.findByUsername(JwtUtil.getUsername()));
    target.setDescription(source.getDescription());
    if (Objects.nonNull(source.getAdditionalInfo())) {
      target.setAdditionalInfo(source.getAdditionalInfo());
    }
  }
}
