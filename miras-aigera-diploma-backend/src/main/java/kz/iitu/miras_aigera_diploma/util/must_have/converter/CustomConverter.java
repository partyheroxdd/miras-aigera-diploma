package kz.iitu.miras_aigera_diploma.util.must_have.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

@FunctionalInterface
public interface CustomConverter<S, T> {

  @NonNull
  T convert(S source);

  default <U> Converter<S, U> andThen(Converter<? super T, ? extends U> after) {
    Assert.notNull(after, "After Converter must not be null");
    return (s) -> {
      T initialResult = this.convert(s);
      return after.convert(initialResult);
    };
  }
}
