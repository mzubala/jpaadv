package pl.com.bottega.qma.catalog;

import pl.com.bottega.qma.core.pagination.PagedResults;

import java.util.List;

public class DocumentSearchResults extends PagedResults<DocumentBasicDetails> {

  public DocumentSearchResults(List<DocumentBasicDetails> results, int pagesCount, int resultsCount, int perPage) {
    super(results, pagesCount, resultsCount, perPage);
  }

}
