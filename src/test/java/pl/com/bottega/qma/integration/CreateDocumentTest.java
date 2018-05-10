package pl.com.bottega.qma.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.qma.docflow.Document;
import pl.com.bottega.qma.docflow.DocumentFlowProcess;
import pl.com.bottega.qma.docflow.DocumentRepository;
import pl.com.bottega.qma.docflow.commands.CreateDocumentCommand;
import pl.com.bottega.qma.docflow.commands.EditDocumentCommand;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CreateDocumentTest {

  @Autowired
  private DocumentFlowProcess documentFlowProcess;

  @Autowired
  private DocumentRepository documentRepository;

  @Test
  public void createsDocument() {
    //given
    CreateDocumentCommand create = new CreateDocumentCommand();
    create.creatorId = 1L;

    //when
    String nr = documentFlowProcess.create(create);

    //then
    Document doc = documentRepository.get(nr);
    assertTrue(doc != null);
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
    editDocumentCommand.editorId = 1L;

    //then
    Document doc = documentRepository.get(nr);
    assertTrue(doc != null);
  }

}
