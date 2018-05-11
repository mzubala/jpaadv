package pl.com.bottega.qma.docflow;

import org.springframework.transaction.support.TransactionTemplate;
import pl.com.bottega.qma.core.events.EventPublisher;
import pl.com.bottega.qma.docflow.commands.*;

import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;

public class DocumentFlowProcess {

  private final DocumentRepository documentRepository;
  private final NumberGenerator numberGenerator;
  private final EventPublisher eventPublisher;
  private TransactionTemplate transactionTemplate;

  public DocumentFlowProcess(DocumentRepository documentRepository,
                             NumberGenerator numberGenerator,
                             EventPublisher eventPublisher,
                             TransactionTemplate transactionTemplate
                             ) {
    this.documentRepository = documentRepository;
    this.numberGenerator = numberGenerator;
    this.eventPublisher = eventPublisher;
    this.transactionTemplate = transactionTemplate;
  }

  @Transactional
  public String create(CreateDocumentCommand cmd) {
    String number = numberGenerator.generate();
    Document document = new Document(number, cmd.creatorId, eventPublisher);
    documentRepository.save(document);
    return number;
  }

  public void edit(EditDocumentCommand cmd) {
    try {
      transactionTemplate.execute((c) -> {
        Document document = documentRepository.get(cmd.documentNumber);
        document.edit(cmd);
        documentRepository.save(document);
        return document;
      });
    }
    catch(OptimisticLockException ex) {
      transactionTemplate.execute((c) -> {
        Document changedDoc = documentRepository.get(cmd.documentNumber);
        changedDoc.smartEdit(cmd);
        documentRepository.save(changedDoc);
        return changedDoc;
      });
    }
  }

  @Transactional
  public void verify(VerifyDocumentCommand cmd) {
    Document document = documentRepository.get(cmd.documentNumber);
    document.verify(cmd);
    documentRepository.save(document);
  }

  @Transactional
  public void publish(PublishDocumentCommand cmd) {
    Document document = documentRepository.get(cmd.documentNumber);
    document.publish(cmd);
    documentRepository.save(document);
  }

  @Transactional
  public void archive(ArchiveDocumentCommand cmd) {
    Document document = documentRepository.get(cmd.documentNumber);
    document.archive(cmd);
    documentRepository.save(document);
  }

}
