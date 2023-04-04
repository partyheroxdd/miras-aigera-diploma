package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.List;
import kz.iitu.miras_aigera_diploma.converter.post_category.PostCategoryListInfoDtoConverter;
import kz.iitu.miras_aigera_diploma.model.dto.post_category.PostCategoryListInfoDto;
import kz.iitu.miras_aigera_diploma.repository.PostCategoryRepository;
import kz.iitu.miras_aigera_diploma.service.PostCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostCategoryServiceImpl implements PostCategoryService {

  PostCategoryRepository postCategoryRepository;

  PostCategoryListInfoDtoConverter postCategoryListInfoDtoConverter;

  @Override
  @Transactional(readOnly = true)
  public List<PostCategoryListInfoDto> findAll() {
    return postCategoryListInfoDtoConverter.convert(postCategoryRepository.findAll());
  }

}
