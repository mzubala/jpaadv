package pl.com.bottega.qma.docflow;

import pl.com.bottega.qma.docflow.commands.ArchiveDocumentCommand;
import pl.com.bottega.qma.docflow.commands.EditDocumentCommand;
import pl.com.bottega.qma.docflow.commands.PublishDocumentCommand;
import pl.com.bottega.qma.docflow.commands.VerifyDocumentCommand;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class Document {

  @Id
  private String number;

  @Enumerated(EnumType.STRING)
  private DocumentStatus documentStatus;

  private boolean contentDefined, titleDefined;

  private Document() {
  }

  public Document(String number, Long creatorId) {
    this.number = number;
    documentStatus = DocumentStatus.DRAFT;
  }

  public void edit(EditDocumentCommand cmd) {
    checkState(DocumentStatus.VERIFIED, DocumentStatus.DRAFT);
    cmd.title.ifPresent((title) -> {
      titleDefined = title.length() > 0;
    });
    cmd.content.ifPresent((content) -> {
      contentDefined = content.length() > 0;
    });
    documentStatus = DocumentStatus.DRAFT;
  }

  public void verify(VerifyDocumentCommand cmd) {
    checkState(DocumentStatus.DRAFT);
    if (!contentDefined || !titleDefined)
      throw new InvalidDocumentOperation();
    documentStatus = DocumentStatus.VERIFIED;
  }

  public void publish(PublishDocumentCommand cmd) {
    checkState(DocumentStatus.VERIFIED);
    documentStatus = DocumentStatus.PUBLISHED;
  }

  public void archive(ArchiveDocumentCommand cmd) {
    documentStatus = DocumentStatus.ARCHIVED;
  }

  private void checkState(DocumentStatus... allowedStates) {
    if (!Arrays.asList(allowedStates).contains(documentStatus))
      throw new InvalidDocumentOperation();
  }

}

enum DocumentStatus {

  DRAFT, VERIFIED, PUBLISHED, ARCHIVED

}
