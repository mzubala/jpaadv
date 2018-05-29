package pl.com.bottega.qma.docflow;

import org.junit.Test;
import pl.com.bottega.qma.docflow.commands.PublishDocumentCommand;

import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class DocumentTest {

  @Test
  public void cantPublishDraftDocuments() {
    Document doc = new Document("1", 1L);

    PublishDocumentCommand cmd = new PublishDocumentCommand();
    cmd.documentNumber = "1";
    cmd.departmentCodes.add("department1");
    cmd.publisherId = 2L;
    assertThatThrownBy(() -> doc.publish(cmd)).isInstanceOf(InvalidDocumentOperation.class);
  }

}
