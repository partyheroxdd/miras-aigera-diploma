package kz.iitu.miras_aigera_diploma.util.spec;

import java.util.Date;
import java.util.Objects;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.model.entity.PostCategory;
import kz.iitu.miras_aigera_diploma.util.must_have.specification.SpecificationBuilder;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class PostUserSpec {

  Specification<Post> resultSpec = new SpecificationBuilder<>();

  public Specification<Post> querySearch(Long userId, Date dateFrom, Date dateTo,
      Long categoryId, Long statusId, String query) {
    resultSpec.and(userFilter(userId));

    if (Objects.nonNull(dateFrom) && Objects.nonNull(dateTo)) {
      resultSpec.and(dateFilter(dateFrom, dateTo));
    }
    if (Objects.nonNull(categoryId)) {
      resultSpec.and(categoryFilter(categoryId));
    }
    if (Objects.nonNull(statusId)) {
      resultSpec.and(statusFilter(statusId));
    }
    if (Objects.nonNull(query)) {
      resultSpec.and(queryFilter(query));
    }
    return resultSpec;
  }

  public Specification<Post> userFilter(Long userId) {
    return (r, cq, cb) -> r.join(Post.Fields.user).in(userId);
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
                "%" + query.toLowerCase() + "%")
        )
    );
  }
}
