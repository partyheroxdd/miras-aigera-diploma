package kz.iitu.miras_aigera_diploma.util.spec;

import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import kz.iitu.miras_aigera_diploma.model.entity.BaseEntity;
import kz.iitu.miras_aigera_diploma.model.entity.City;
import kz.iitu.miras_aigera_diploma.model.entity.District;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.model.entity.PostCategory;
import kz.iitu.miras_aigera_diploma.model.entity.PostStatus;
import kz.iitu.miras_aigera_diploma.model.enums.CategoryCode;
import kz.iitu.miras_aigera_diploma.model.enums.StatusCode;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class PostSpec {

  public Specification<Post> userFilter(Long userId) {
    return (r, cq, cb) -> cb.equal(r.get(Post.Fields.user).get(BaseEntity.Fields.id), userId);
  }

  public Specification<Post> districtFilter(String district) {
    return (r, cq, cb) -> cb.equal(r.get(Post.Fields.district).get(District.Fields.name), district);
  }

  public Specification<Post> cityFilter(String city) {
    return (r, cq, cb) -> cb.equal(r.get(Post.Fields.city).get(City.Fields.name), city);
  }

  public Specification<Post> categoryFilter(CategoryCode category) {
    return (r, cq, cb) -> cb.equal(r.get(Post.Fields.category).get(PostCategory.Fields.code),
        category);
  }

  public Specification<Post> statusFilter(StatusCode status) {
    return (r, cq, cb) -> cb.equal(r.get(Post.Fields.status).get(PostStatus.Fields.code), status);
  }

  public Specification<Post> dateFilter(Date dateFrom, Date dateTo) {
    return (r, cq, cb) -> cb.and(cb.greaterThanOrEqualTo(r.get(Post.Fields.createdAt), dateFrom),
        cb.lessThan(r.get(Post.Fields.createdAt), DateUtils.addDays(dateTo, 1)));
  }

  public Specification<Post> queryFilter(String query) {
    return (root, cq, cb) -> cb.and(cb.like(cb.lower(root.get(Post.Fields.number).as(String.class)),
        "%" + query.toLowerCase() + "%"));
  }

  public Specification<Post> postOrderByCreatedDate() {
    return (r, cq, cb) -> {
      Path<Post> field = r.get(Post.Fields.createdAt);
      switchDirection(Direction.DESC, cq, cb, field);
      return cb.and();
    };
  }

  private void switchDirection(
      Sort.Direction direction, CriteriaQuery<?> cq, CriteriaBuilder cb, Path<?> field) {
    switch (direction) {
      case ASC:
        cq.orderBy(cb.asc(field));
        break;
      case DESC:
        cq.orderBy(cb.desc(field));
        break;
    }
  }
}
