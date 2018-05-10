package pl.com.bottega.qma.docflow.events;

import pl.com.bottega.qma.core.events.Event;

public class DocumentCreatedEvent implements Event {

  public String number;
  public Long creatorId;

  public DocumentCreatedEvent(String number, Long creatorId) {
    this.number = number;
    this.creatorId = creatorId;
  }

}
