package pl.com.bottega.qma.confirmation;

import pl.com.bottega.qma.docflow.DocumentNotFoundException;

import javax.persistence.EntityManager;

public class JpaConfirmationRepository implements ConfirmationRepository {

  private final EntityManager entityManager;

  public JpaConfirmationRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void save(Confirmation confirmation) {
    entityManager.persist(confirmation);
  }

  @Override
  public Confirmation get(String documentNumber, Long confirmerId) {
    Confirmation confirmation = entityManager.find(Confirmation.class, new Confirmation.ConfirmationId(documentNumber, confirmerId));
    if(confirmation == null)
      throw new DocumentNotFoundException();
    return confirmation;
  }
}
