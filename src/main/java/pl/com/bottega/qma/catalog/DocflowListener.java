package pl.com.bottega.qma.catalog;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.com.bottega.qma.docflow.events.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
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

  @EventListener
  public void onDocumentTitleChange(DocumentTitleChangedEvent event) {
    DocumentDetails documentDetails = entityManager.find(DocumentDetails.class, event.documentNumber);
    documentDetails.editors.add(event.editorId);
    documentDetails.title = event.newTitle;
    documentDetails.status = "DRAFT";
  }

  @EventListener
  public void onDocumentContentChange(DocumentContentChangedEvent event) {
    DocumentDetails documentDetails = entityManager.find(DocumentDetails.class, event.documentNumber);
    documentDetails.editors.add(event.editorId);
    documentDetails.title = event.newContent;
    documentDetails.status = "DRAFT";
  }

  @EventListener
  public void onDocumentVerified(DocumentVerifiedEvent event) {
    DocumentDetails documentDetails = entityManager.find(DocumentDetails.class, event.documentNumber);
    documentDetails.status = "VERIFIED";
  }

  @EventListener
  public void onDocumentPublished(DocumentPublishedEvent event) {
    DocumentDetails documentDetails = entityManager.find(DocumentDetails.class, event.documentNumber);
    documentDetails.status = "PUBLISHED";
  }

  @EventListener
  public void onDocumentArchived(DocumentArchivedEvent event) {
    DocumentDetails documentDetails = entityManager.find(DocumentDetails.class, event.documentNumber);
    documentDetails.status = "ARCHIVED";
  }

}
