package pl.com.bottega.qma.docflow;

import pl.com.bottega.qma.docflow.commands.EditDocumentCommand;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Document {

  @Id
  private String number;

  @Enumerated(EnumType.STRING)
  private DocumentStatus documentStatus;

  private Document() {
  }

  public Document(String number, Long creatorId) {
    this.number = number;
    documentStatus = DocumentStatus.DRAFT;
  }

  public void edit(EditDocumentCommand cmd) {
    if (documentStatus != DocumentStatus.VERIFIED && documentStatus != DocumentStatus.DRAFT)
      throw new InvalidDocumentOperation();
    documentStatus = DocumentStatus.DRAFT;
  }
}

enum DocumentStatus {

  DRAFT, VERIFIED, PUBLISHED, ARCHIVED

}
