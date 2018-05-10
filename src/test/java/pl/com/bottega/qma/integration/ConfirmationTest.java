package pl.com.bottega.qma.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.qma.confirmation.Confirmation;
import pl.com.bottega.qma.confirmation.DocumentConfirmationProcess;
import pl.com.bottega.qma.confirmation.commands.ConfirmDocumentCommand;
import pl.com.bottega.qma.docflow.DocumentFlowProcess;
import pl.com.bottega.qma.docflow.commands.CreateDocumentCommand;
import pl.com.bottega.qma.docflow.commands.EditDocumentCommand;
import pl.com.bottega.qma.docflow.commands.PublishDocumentCommand;
import pl.com.bottega.qma.docflow.commands.VerifyDocumentCommand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfirmationTest {

  @Autowired
  private DocumentFlowProcess documentFlowProcess;

  @Autowired
  private DocumentConfirmationProcess documentConfirmationProcess;

  @Test
  public void confirmsDocument() {
    //given
    CreateDocumentCommand create = new CreateDocumentCommand();
    create.creatorId = 1L;
    String nr = documentFlowProcess.create(create);
    EditDocumentCommand editDocumentCommand = new EditDocumentCommand();
    editDocumentCommand.documentNumber = nr;
    editDocumentCommand.title = Optional.of("test");
    editDocumentCommand.content = Optional.of("test");
    editDocumentCommand.editorId = 1L;
    documentFlowProcess.edit(editDocumentCommand);
    VerifyDocumentCommand verifyDocumentCommand = new VerifyDocumentCommand();
    verifyDocumentCommand.verifierId = 1L;
    verifyDocumentCommand.documentNumber = nr;
    documentFlowProcess.verify(verifyDocumentCommand);
    PublishDocumentCommand publishDocumentCommand = new PublishDocumentCommand();
    publishDocumentCommand.documentNumber = nr;
    publishDocumentCommand.departmentCodes = new HashSet<>(Arrays.asList("D1", "D2"));
    documentFlowProcess.publish(publishDocumentCommand);

    //when
    ConfirmDocumentCommand confirmDocumentCommand = new ConfirmDocumentCommand();
    confirmDocumentCommand.confirmerId = 2L;
    confirmDocumentCommand.documentNumber = nr;
    documentConfirmationProcess.confirm(confirmDocumentCommand);

  }

}
