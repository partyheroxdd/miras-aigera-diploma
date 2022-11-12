package kz.iitu.miras_aigera_diploma.repository;

import java.time.LocalDateTime;
import java.util.List;
import kz.iitu.miras_aigera_diploma.model.entity.LostThingPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostThingPostRepository extends JpaRepository<LostThingPost, Long> {

  public List<LostThingPost> findLostThingPostsByApprovedIsTrue();

  public boolean existsByDateTimeBetween(LocalDateTime from, LocalDateTime to);
}
