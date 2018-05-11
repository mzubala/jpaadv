package pl.com.bottega.qma.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.com.bottega.qma.catalog.DocumentCatalog;
import pl.com.bottega.qma.catalog.DocumentDetails;
import pl.com.bottega.qma.catalog.DocumentSearchResults;
import pl.com.bottega.qma.catalog.SearchDocumentsQuery;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DocumentCatalogTest {

  @Autowired
  private EntityManager entityManager;

  @Autowired
  private TransactionTemplate tt;

  @Autowired
  private DocumentCatalog catalog;

  @Before
  public void insertDocs() {
    DocumentDetails d1 = new DocumentDetails();
    d1.status = "DRAFT";
    d1.content = "test";
    d1.number = "1";
    d1.title = "test";

    DocumentDetails d2 = new DocumentDetails();
    d2.status = "VERIFIED";
    d2.content = "test ala";
    d2.number = "2";
    d2.title = "test";

    DocumentDetails d3 = new DocumentDetails();
    d3.status = "PUBLISHED";
    d3.content = "test";
    d3.number = "3";
    d3.title = "test ala";

    tt.execute((c) -> {
      entityManager.persist(d1);
      entityManager.persist(d2);
      entityManager.persist(d3);
      return null;
    });
  }

  @Test
  public void searchesDocuments() {
    SearchDocumentsQuery searchDocumentsQuery = new SearchDocumentsQuery();
    searchDocumentsQuery.query = "al";

    DocumentSearchResults res = catalog.search(searchDocumentsQuery);

    assertEquals(2, res.resultsCount);
    assertEquals(2, res.results.size());
  }

}
