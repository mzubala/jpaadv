package pl.com.bottega.qma.docflow;

import pl.com.bottega.qma.docflow.commands.*;

public class DocumentFlowProcess {

  private final DocumentRepository documentRepository;
  private final NumberGenerator numberGenerator;

  public DocumentFlowProcess(DocumentRepository documentRepository,
                             NumberGenerator numberGenerator
                             ) {
    this.documentRepository = documentRepository;
    this.numberGenerator = numberGenerator;
  }

  public String create(CreateDocumentCommand cmd) {
    String number = numberGenerator.generate();
    Document document = new Document(number, cmd.creatorId);
    documentRepository.save(document);
    return number;
  }

  public void edit(EditDocumentCommand cmd) {
    Document document = documentRepository.get(cmd.documentNumber);
    document.edit(cmd);
    documentRepository.save(document);
  }

  public void verify(VerifyDocumentCommand cmd) {
    Document document = documentRepository.get(cmd.documentNumber);
    document.verify(cmd);
    documentRepository.save(document);
  }

  public void publish(PublishDocumentCommand cmd) {
    Document document = documentRepository.get(cmd.documentNumber);
    document.publish(cmd);
    documentRepository.save(document);
  }

  public void archive(ArchiveDocumentCommand cmd) {
    Document document = documentRepository.get(cmd.documentNumber);
    document.archive(cmd);
    documentRepository.save(document);
  }

}
