package pl.com.bottega.qma.docflow.events;

import pl.com.bottega.qma.core.events.Event;

public class DocumentContentChangedEvent implements Event {

  public String documentNumber;
  public String newContent;
  public Long editorId;

  public DocumentContentChangedEvent(String documentNumber, String newContent, Long editorId) {
    this.documentNumber = documentNumber;
    this.newContent = newContent;
    this.editorId = editorId;
  }
}
