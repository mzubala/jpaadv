package pl.com.bottega.qma.confirmation;

import pl.com.bottega.qma.confirmation.commands.ConfirmDocumentCommand;
import pl.com.bottega.qma.confirmation.commands.ConfirmDocumentOnBehalfCommand;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Confirmation {

  @EmbeddedId
  private ConfirmationId id;

  private Long managerId;

  private Instant confirmedAt;

  private Confirmation() {}

  public Confirmation(String documentNumber, Long employeeId) {
    this.id = new ConfirmationId(documentNumber, employeeId);
  }

  public void confirm(ConfirmDocumentCommand cmd) {
    if(confirmedAt != null)
      return;
    confirmedAt = Instant.now();
  }

  public void confirmOnBehalf(ConfirmDocumentOnBehalfCommand cmd) {
    if(confirmedAt != null)
      return;
    confirmedAt = Instant.now();
    managerId = cmd.managerId;
  }


  @Embeddable
  static class ConfirmationId implements Serializable {
    private String documentNumber;

    private Long employeeId;

    private ConfirmationId() {
    }

    public ConfirmationId(String documentNumber, Long employeeId) {
      this.documentNumber = documentNumber;
      this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ConfirmationId that = (ConfirmationId) o;
      return Objects.equals(documentNumber, that.documentNumber) &&
          Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(documentNumber, employeeId);
    }
  }
}
