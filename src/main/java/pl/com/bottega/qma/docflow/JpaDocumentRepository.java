package pl.com.bottega.qma.docflow;

import javax.persistence.EntityManager;

public class JpaDocumentRepository implements DocumentRepository {

  private final EntityManager entityManager;

  public JpaDocumentRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void save(Document document) {
    entityManager.persist(document);
  }

  @Override
  public Document get(String documentNumber) {
    Document document = entityManager.find(Document.class, documentNumber);
    if (document == null)
      throw new DocumentNotFoundException();
    return document;
  }
}
