package pl.com.bottega.qma.docflow.events;

import pl.com.bottega.qma.core.events.Event;

public class DocumentVerifiedEvent implements Event {

  public String documentNumber;
  Long verifierId;

  public DocumentVerifiedEvent(String documentNumber, Long verifierId) {
    this.documentNumber = documentNumber;
    this.verifierId = verifierId;
  }


}
