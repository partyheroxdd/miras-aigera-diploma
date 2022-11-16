package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.entity.PostCategory;
import kz.iitu.miras_aigera_diploma.repository.PostCategoryRepository;
import kz.iitu.miras_aigera_diploma.service.PostCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCategoryServiceImpl implements PostCategoryService {
  private final PostCategoryRepository postCategoryRepository;

  @Override
  public List<PostCategory> findAll() {
    return postCategoryRepository.findAll();
  }
}
