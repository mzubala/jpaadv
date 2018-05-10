package pl.com.bottega.qma.docflow.events;

import pl.com.bottega.qma.core.events.Event;

import java.util.Set;

public class DocumentPublishedEvent implements Event {

  public String documentNumber;

  public Long publisherId;

  public Set<String> departmentCodes;

  public DocumentPublishedEvent(String documentNumber, Long publisherId, Set<String> departmentCodes) {
    this.documentNumber = documentNumber;
    this.publisherId = publisherId;
    this.departmentCodes = departmentCodes;
  }
}
