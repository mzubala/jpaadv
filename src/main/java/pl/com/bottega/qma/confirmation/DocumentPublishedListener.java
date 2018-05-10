package pl.com.bottega.qma.confirmation;


import org.springframework.context.event.EventListener;
import pl.com.bottega.qma.docflow.events.DocumentPublishedEvent;

public class DocumentPublishedListener {

  private final DocumentConfirmationProcess confirmationProcess;

  public DocumentPublishedListener(DocumentConfirmationProcess confirmationProcess) {
    this.confirmationProcess = confirmationProcess;
  }

  @EventListener
  public void onDocumentPublished(DocumentPublishedEvent event) {
    confirmationProcess.createConfirmations(event);
  }

}
