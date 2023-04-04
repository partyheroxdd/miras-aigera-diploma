package kz.iitu.miras_aigera_diploma.util.must_have.query;

import kz.iitu.miras_aigera_diploma.util.must_have.specification.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

public final class CommonSpec {

  private CommonSpec() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static <T> Specification<T> search(@Nullable String query, @Nullable String... fields) {
    if (fields != null && fields.length >= 1) {
      SpecificationBuilder<T> output = new SpecificationBuilder();
      if (query != null) {
        String[] var3 = fields;
        int var4 = fields.length;

        for (int var5 = 0; var5 < var4; ++var5) {
          String str = var3[var5];
          output.or((root, qb, builder) -> {
            return builder.like(builder.lower(root.get(str).as(String.class)),
                "%" + query.toLowerCase() + "%");
          });
        }
      }

      return output;
    } else {
      throw new IllegalArgumentException("'fields' parameter can not be null or empty");
    }
  }
}
