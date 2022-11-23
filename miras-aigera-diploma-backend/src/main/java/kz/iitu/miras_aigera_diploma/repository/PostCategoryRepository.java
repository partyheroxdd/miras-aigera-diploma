package kz.iitu.miras_aigera_diploma.repository;

import kz.iitu.miras_aigera_diploma.model.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {

  PostCategory findByName(String postCategoryName);
}
