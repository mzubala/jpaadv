package pl.com.bottega.qma.confirmation.events;

import pl.com.bottega.qma.core.events.Event;

import java.util.Optional;

public class DocumentConfirmedEvent implements Event {

  public String documentNumber;

  public Long employeeId;

  public Optional<Long> managerId;

  public DocumentConfirmedEvent(String documentNumber, Long employeeId) {
    this.documentNumber = documentNumber;
    this.employeeId = employeeId;
    this.managerId = Optional.empty();
  }

  public DocumentConfirmedEvent(String documentNumber, Long employeeId, Long managerId) {
    this.documentNumber = documentNumber;
    this.employeeId = employeeId;
    this.managerId = Optional.of(managerId);
  }

}
