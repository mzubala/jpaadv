package pl.com.bottega.qma.docflow.events;

import pl.com.bottega.qma.core.events.Event;

public class DocumentArchivedEvent implements Event {

  public String documentNumber;
  public Long archiverId;

  public DocumentArchivedEvent(String documentNumber, Long archiverId) {
    this.documentNumber = documentNumber;
    this.archiverId = archiverId;
  }
}
