package pl.com.bottega.qma.docflow;

import javax.persistence.EntityManager;

public class JpaDocumentRepository implements DocumentRepository {

  private final EntityManager entityManager;

  public JpaDocumentRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void save(Document document) {

  }
}
