package pl.com.bottega.qma.docflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import pl.com.bottega.qma.core.events.EventPublisher;
import pl.com.bottega.qma.docflow.commands.ArchiveDocumentCommand;
import pl.com.bottega.qma.docflow.commands.EditDocumentCommand;
import pl.com.bottega.qma.docflow.commands.PublishDocumentCommand;
import pl.com.bottega.qma.docflow.commands.VerifyDocumentCommand;
import pl.com.bottega.qma.docflow.events.*;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class Document {

  @Id
  private String number;

  @Autowired
  private transient EventPublisher eventPublisher;

  @Enumerated(EnumType.STRING)
  private DocumentStatus documentStatus;

  private boolean contentDefined, titleDefined;

  @Version
  private Long version;

  private Document() {
  }

  public Document(String number, Long creatorId, EventPublisher eventPublisher) {
    this.number = number;
    this.eventPublisher = eventPublisher;
    documentStatus = DocumentStatus.DRAFT;
    eventPublisher.publish(new DocumentCreatedEvent(number, creatorId));
  }

  public void edit(EditDocumentCommand cmd) {
    checkState(DocumentStatus.VERIFIED, DocumentStatus.DRAFT);
    cmd.title.ifPresent((title) -> {
      titleDefined = title.length() > 0;
      eventPublisher.publish(new DocumentTitleChangedEvent(number, title, cmd.editorId));
    });
    cmd.content.ifPresent((content) -> {
      contentDefined = content.length() > 0;
      eventPublisher.publish(new DocumentContentChangedEvent(number, content, cmd.editorId));
    });
    documentStatus = DocumentStatus.DRAFT;
  }

  public void verify(VerifyDocumentCommand cmd) {
    checkState(DocumentStatus.DRAFT);
    if (!contentDefined || !titleDefined)
      throw new InvalidDocumentOperation();
    documentStatus = DocumentStatus.VERIFIED;
    eventPublisher.publish(new DocumentVerifiedEvent(number, cmd.verifierId));
  }

  public void publish(PublishDocumentCommand cmd) {
    checkState(DocumentStatus.VERIFIED);
    documentStatus = DocumentStatus.PUBLISHED;
    eventPublisher.publish(new DocumentPublishedEvent(number, cmd.publisherId, cmd.departmentCodes));
  }

  public void archive(ArchiveDocumentCommand cmd) {
    documentStatus = DocumentStatus.ARCHIVED;
    eventPublisher.publish(new DocumentArchivedEvent(number, cmd.archiverId));
  }

  private void checkState(DocumentStatus... allowedStates) {
    if (!Arrays.asList(allowedStates).contains(documentStatus))
      throw new InvalidDocumentOperation();
  }

  public void smartEdit(EditDocumentCommand cmd) {

  }
}

enum DocumentStatus {

  DRAFT, VERIFIED, PUBLISHED, ARCHIVED

}
