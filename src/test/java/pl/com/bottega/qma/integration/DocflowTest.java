package pl.com.bottega.qma.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.com.bottega.qma.docflow.Document;
import pl.com.bottega.qma.docflow.DocumentFlowProcess;
import pl.com.bottega.qma.docflow.DocumentNotFoundException;
import pl.com.bottega.qma.docflow.DocumentRepository;
import pl.com.bottega.qma.docflow.commands.CreateDocumentCommand;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DocflowTest {

  @Autowired
  private DocumentRepository documentRepository;

  @Autowired
  private DocumentFlowProcess documentFlowProcess;

  @Autowired
  private TransactionTemplate tt;

  @Autowired
  private EntityManager entityManager;

  @Test
  public void createsDocument() {
    CreateDocumentCommand cmd = new CreateDocumentCommand();
    cmd.creatorId = 1L;

    String nr = documentFlowProcess.create(cmd);

    assertThat(documentRepository.get(nr)).isNotNull();
  }

  @Test
  public void documentNumberMustBeUnique() {
    Document document = new Document("11", 1L);
    tt.execute((c) -> {
      entityManager.persist(document);
      return null;
    });

    assertThatThrownBy(() -> {
      tt.execute((c) -> {
        documentRepository.save(document);
        return null;
      });
    }).isInstanceOf(DataIntegrityViolationException.class);
  }
}
