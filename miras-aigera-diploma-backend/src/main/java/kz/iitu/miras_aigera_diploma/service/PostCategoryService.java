package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.entity.PostCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface PostCategoryService {
  List<PostCategory> findAll();
}
