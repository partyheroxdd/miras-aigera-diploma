package kz.iitu.miras_aigera_diploma.util;

import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.model.entity.Post.Fields;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class PostSpec {


  public Specification<Post> cityFilter(String city) {
    return (r, cq, cb) -> cb.equal(r.get(Post.Fields.city), city);
  }

  public Specification<Post> approvedFilter(boolean approved) {
    return (r, cq, cb) -> cb.equal(r.get(Fields.approved), approved);
  }

}
