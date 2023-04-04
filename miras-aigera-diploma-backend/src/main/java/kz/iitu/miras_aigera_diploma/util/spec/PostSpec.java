package kz.iitu.miras_aigera_diploma.util.spec;

import java.util.Date;
import kz.iitu.miras_aigera_diploma.model.entity.City;
import kz.iitu.miras_aigera_diploma.model.entity.District;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.model.entity.PostCategory;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class PostSpec {

  public Specification<Post> userFilter(Long userId) {
    return (r, cq, cb) -> r.join(Post.Fields.user).in(userId);
  }

  public Specification<Post> districtFilter(Long districtId) {
    return (r, cq, cb) -> r.join(Post.Fields.district).in(districtId);
  }

  public Specification<Post> cityFilter(Long cityId) {
    return (r, cq, cb) -> r.join(Post.Fields.city).in(cityId);
  }

  public Specification<Post> categoryFilter(Long categoryId) {
    return (r, cq, cb) -> r.join(Post.Fields.category).in(categoryId);
  }

  public Specification<Post> statusFilter(Long statusId) {
    return (r, cq, cb) -> r.join(Post.Fields.status).in(statusId);
  }

  public Specification<Post> dateFilter(Date dateFrom, Date dateTo) {
    return (r, cq, cb) -> cb.and(cb.greaterThanOrEqualTo(r.get(Post.Fields.createdAt), dateFrom),
        cb.lessThan(r.get(Post.Fields.createdAt), DateUtils.addDays(dateTo, 1)));
  }

  public Specification<Post> queryFilter(String query) {
    return (root, cq, cb) -> cb.and(cb.or(
            cb.like(cb.lower(root.get(Post.Fields.category)
                .get(PostCategory.Fields.name).as(String.class)), "%" + query.toLowerCase() + "%"),
            cb.like(cb.lower(root.get(Post.Fields.number).as(String.class)),
                "%" + query.toLowerCase() + "%"),
            cb.like(cb.lower(root.get(Post.Fields.district)
                    .get(District.Fields.name).as(String.class)),
                "%" + query.toLowerCase() + "%"),
            cb.like(cb.lower(root.get(Post.Fields.city)
                    .get(City.Fields.name).as(String.class)),
                "%" + query.toLowerCase() + "%")
        )
    );
  }
}
