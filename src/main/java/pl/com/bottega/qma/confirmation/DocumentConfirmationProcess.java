package pl.com.bottega.qma.confirmation;

import pl.com.bottega.qma.confirmation.commands.ConfirmDocumentCommand;
import pl.com.bottega.qma.confirmation.commands.ConfirmDocumentOnBehalfCommand;
import pl.com.bottega.qma.core.events.EventPublisher;
import pl.com.bottega.qma.docflow.events.DocumentPublishedEvent;
import pl.com.bottega.qma.hr.HrFacade;

import javax.transaction.Transactional;

@Transactional
public class DocumentConfirmationProcess {

  private final ConfirmationRepository confirmationRepository;
  private final HrFacade hrFacade;
  private final EventPublisher eventPublisher;

  public DocumentConfirmationProcess(ConfirmationRepository confirmationRepository,
                                     HrFacade hrFacade,
                                     EventPublisher eventPublisher) {
    this.confirmationRepository = confirmationRepository;
    this.hrFacade = hrFacade;
    this.eventPublisher = eventPublisher;
  }

  public void createConfirmations(DocumentPublishedEvent event) {
    hrFacade.getEmployeesInDepartments(event.departmentCodes).forEach((employeeId) -> {
      Confirmation confirmation = new Confirmation(event.documentNumber, employeeId, eventPublisher);
      confirmationRepository.save(confirmation);
    });
  }

  public void confirm(ConfirmDocumentCommand cmd) {
    Confirmation confirmation = confirmationRepository.get(cmd.documentNumber, cmd.confirmerId);
    confirmation.confirm(cmd);
    confirmationRepository.save(confirmation);
  }

  public void confirmFor(ConfirmDocumentOnBehalfCommand cmd) {
    Confirmation confirmation = confirmationRepository.get(cmd.documentNumber, cmd.confirmerId);
    confirmation.confirmOnBehalf(cmd);
    confirmationRepository.save(confirmation);
  }


}
