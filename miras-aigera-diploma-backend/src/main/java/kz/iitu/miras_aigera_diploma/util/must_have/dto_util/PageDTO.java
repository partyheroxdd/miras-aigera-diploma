package kz.iitu.miras_aigera_diploma.util.must_have.dto_util;

import java.util.ArrayList;
import java.util.List;

public class PageDTO<T> {

  private Long total;
  private Integer pageNumber;
  private Integer totalPages;
  private List<T> content;

  public PageDTO() {
    this.total = 0L;
    this.pageNumber = 0;
    this.totalPages = 0;
    this.content = new ArrayList();
  }

  public static <T> PageDTO<T> empty() {
    return new PageDTO();
  }

  public Long getTotal() {
    return this.total;
  }

  public Integer getPageNumber() {
    return this.pageNumber;
  }

  public Integer getTotalPages() {
    return this.totalPages;
  }

  public List<T> getContent() {
    return this.content;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }

  public PageDTO(Long total, Integer pageNumber, Integer totalPages, List<T> content) {
    this.total = total;
    this.pageNumber = pageNumber;
    this.totalPages = totalPages;
    this.content = content;
  }
}
