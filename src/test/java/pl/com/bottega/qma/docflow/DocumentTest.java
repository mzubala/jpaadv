package pl.com.bottega.qma.docflow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.bottega.qma.core.events.EventPublisher;
import pl.com.bottega.qma.docflow.commands.PublishDocumentCommand;
import pl.com.bottega.qma.docflow.events.DocumentCreatedEvent;

import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DocumentTest {

  @Mock
  private EventPublisher eventPublisher;

  @Test
  public void cantPublishDraftDocuments() {
    Document doc = new Document("1", 1L, eventPublisher);

    PublishDocumentCommand cmd = new PublishDocumentCommand();
    cmd.documentNumber = "1";
    cmd.departmentCodes.add("department1");
    cmd.publisherId = 2L;
    assertThatThrownBy(() -> doc.publish(cmd)).isInstanceOf(InvalidDocumentOperation.class);
  }

  @Test
  public void publishesCreatedEvent() {
    Document doc = new Document("1", 1L, eventPublisher);

    verify(eventPublisher).publish(new DocumentCreatedEvent("1", 1L));
  }

}
