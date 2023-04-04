package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.post_category.PostCategoryListInfoDto;

public interface PostCategoryService {

  List<PostCategoryListInfoDto> findAll();

}
