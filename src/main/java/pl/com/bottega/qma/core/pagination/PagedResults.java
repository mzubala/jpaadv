package pl.com.bottega.qma.core.pagination;

import java.util.List;

public class PagedResults<T> {

  public List<T> results;

  public int pagesCount, resultsCount, perPage;

  public PagedResults(List<T> results, int pagesCount, int resultsCount, int perPage) {
    this.results = results;
    this.pagesCount = pagesCount;
    this.resultsCount = resultsCount;
    this.perPage = perPage;
  }
}
