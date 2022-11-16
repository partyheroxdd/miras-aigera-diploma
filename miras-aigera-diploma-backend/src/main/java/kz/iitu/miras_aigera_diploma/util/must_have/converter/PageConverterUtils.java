package kz.iitu.miras_aigera_diploma.util.must_have.converter;

import java.util.List;
import kz.iitu.miras_aigera_diploma.util.must_have.dto_util.PageDTO;
import org.springframework.data.domain.Page;

public class PageConverterUtils {

  public PageConverterUtils() {
  }

  public static <T> PageDTO<T> convert(Page<T> page) {
    return convert(page, page.getContent());
  }

  public static <T> PageDTO<T> convert(Page<?> page, List<T> content) {
    return of(content, page.getTotalElements(), page.getNumber(), page.getTotalPages());
  }

  public static <T> PageDTO<T> of(List<T> content, long total, int pageNumber, int pageTotal) {
    PageDTO<T> pageDTO = new PageDTO();
    pageDTO.setContent(content);
    pageDTO.setTotal(total);
    pageDTO.setPageNumber(pageNumber);
    pageDTO.setTotalPages(pageTotal);
    return pageDTO;
  }
}
