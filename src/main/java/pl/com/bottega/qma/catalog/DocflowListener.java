package pl.com.bottega.qma.catalog;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.com.bottega.qma.docflow.events.DocumentCreatedEvent;

import javax.persistence.EntityManager;

@Component
public class DocflowListener {

  private final EntityManager entityManager;

  public DocflowListener(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @EventListener
  public void onDocumentCreate(DocumentCreatedEvent event) {
    DocumentDetails documentDetails = new DocumentDetails();
    documentDetails.number = event.number;
    documentDetails.status = "DRAFT";
    documentDetails.creatorId = event.creatorId;
    entityManager.persist(documentDetails);
  }

}
