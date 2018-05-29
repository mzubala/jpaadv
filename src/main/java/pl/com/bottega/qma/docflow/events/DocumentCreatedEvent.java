package pl.com.bottega.qma.docflow.events;

import pl.com.bottega.qma.core.events.Event;

import java.util.Objects;

public class DocumentCreatedEvent implements Event {

  public String number;
  public Long creatorId;

  public DocumentCreatedEvent(String number, Long creatorId) {
    this.number = number;
    this.creatorId = creatorId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DocumentCreatedEvent that = (DocumentCreatedEvent) o;
    return Objects.equals(number, that.number) &&
        Objects.equals(creatorId, that.creatorId);
  }

  @Override
  public int hashCode() {

    return Objects.hash(number, creatorId);
  }
}
