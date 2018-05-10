package pl.com.bottega.qma.docflow.events;

import pl.com.bottega.qma.core.events.Event;

public class DocumentTitleChangedEvent implements Event {

  public String documentNumber;
  public String newTitle;
  private Long editorId;

  public DocumentTitleChangedEvent(String documentNumber, String newTitle, Long editorId) {
    this.documentNumber = documentNumber;
    this.newTitle = newTitle;
    this.editorId = editorId;
  }
}
