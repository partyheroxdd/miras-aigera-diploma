package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.List;
import kz.iitu.miras_aigera_diploma.converter.post_category.PostCategoryInfoDtoConverter;
import kz.iitu.miras_aigera_diploma.exceptions.NotFoundException;
import kz.iitu.miras_aigera_diploma.model.Constants.ApiMessages;
import kz.iitu.miras_aigera_diploma.model.dto.post_category.PostCategoryInfoDto;
import kz.iitu.miras_aigera_diploma.repository.PostCategoryRepository;
import kz.iitu.miras_aigera_diploma.service.PostCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCategoryServiceImpl implements PostCategoryService {

  private final PostCategoryRepository postCategoryRepository;
  private final PostCategoryInfoDtoConverter postCategoryInfoDtoConverter;

  @Override
  public List<PostCategoryInfoDto> findAll() {
    return postCategoryInfoDtoConverter.convert(postCategoryRepository.findAll());
  }

  @Override
  public PostCategoryInfoDto findById(Long id) {
    return postCategoryInfoDtoConverter.convert(
        postCategoryRepository.findById(id).orElseThrow(() -> new NotFoundException(
            ApiMessages.ID_NOT_FOUND, HttpStatus.BAD_REQUEST)));
  }
}
