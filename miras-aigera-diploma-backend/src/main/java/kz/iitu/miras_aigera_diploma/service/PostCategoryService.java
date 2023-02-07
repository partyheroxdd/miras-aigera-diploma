package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.post_category.PostCategoryInfoDto;

public interface PostCategoryService {

  List<PostCategoryInfoDto> findAll();

  PostCategoryInfoDto findById(Long id);
}
