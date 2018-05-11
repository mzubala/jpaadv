package pl.com.bottega.qma.catalog;

import org.springframework.stereotype.Component;
import pl.com.bottega.qma.docflow.DocumentNotFoundException;

import javax.persistence.EntityManager;

@Component
public class DocumentCatalog {

  private final EntityManager entityManager;

  public DocumentCatalog(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public DocumentDetails get(String number) {
    DocumentDetails doc = entityManager.find(DocumentDetails.class, number);
    if(doc == null)
      throw new DocumentNotFoundException();
    return doc;
  }

  public DocumentSearchResults search(SearchDocumentsQuery query) {
    return null;
  }

}
