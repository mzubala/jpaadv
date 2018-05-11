package pl.com.bottega.qma.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.qma.catalog.DocumentCatalog;
import pl.com.bottega.qma.catalog.DocumentDetails;
import pl.com.bottega.qma.docflow.Document;
import pl.com.bottega.qma.docflow.DocumentFlowProcess;
import pl.com.bottega.qma.docflow.DocumentRepository;
import pl.com.bottega.qma.docflow.commands.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DocumentFlowTest {

  @Autowired
  private DocumentFlowProcess documentFlowProcess;

  @Autowired
  private DocumentCatalog documentCatalog;

  @Test
  public void createsDocument() {
    //given
    CreateDocumentCommand create = new CreateDocumentCommand();
    create.creatorId = 1L;

    //when
    String nr = documentFlowProcess.create(create);

    //then
    DocumentDetails documentDetails = documentCatalog.get(nr);
    assertEquals(nr, documentDetails.number);
    assertEquals("DRAFT", documentDetails.status);
    assertEquals(new Long(1L), documentDetails.creatorId);
  }

  @Test
  public void editsDocument() {
    //given
    CreateDocumentCommand create = new CreateDocumentCommand();
    create.creatorId = 1L;
    String nr = documentFlowProcess.create(create);

    //when
    EditDocumentCommand editDocumentCommand = new EditDocumentCommand();
    editDocumentCommand.documentNumber = nr;
    editDocumentCommand.title = Optional.of("test");
    editDocumentCommand.content = Optional.empty();
    editDocumentCommand.editorId = 1L;
    documentFlowProcess.edit(editDocumentCommand);
  }

  @Test
  public void verifiesDocument() {
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

    //when
    VerifyDocumentCommand verifyDocumentCommand = new VerifyDocumentCommand();
    verifyDocumentCommand.verifierId = 1L;
    verifyDocumentCommand.documentNumber = nr;
    documentFlowProcess.verify(verifyDocumentCommand);
  }


  @Test
  public void publishesDocument() {
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

    //when
    PublishDocumentCommand publishDocumentCommand = new PublishDocumentCommand();
    publishDocumentCommand.documentNumber = nr;
    publishDocumentCommand.departmentCodes = new HashSet<>(Arrays.asList("D1", "D2"));
    documentFlowProcess.publish(publishDocumentCommand);
  }

  @Test
  public void archivesDocument() {
    //given
    CreateDocumentCommand create = new CreateDocumentCommand();
    create.creatorId = 1L;
    String nr = documentFlowProcess.create(create);

    //when
    ArchiveDocumentCommand archiveDocumentCommand = new ArchiveDocumentCommand();
    archiveDocumentCommand.archiverId = 1L;
    archiveDocumentCommand.documentNumber = nr;
    documentFlowProcess.archive(archiveDocumentCommand);
  }

}
