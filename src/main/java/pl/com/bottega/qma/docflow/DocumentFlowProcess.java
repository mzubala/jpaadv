package pl.com.bottega.qma.docflow;

import pl.com.bottega.qma.docflow.commands.*;

public class DocumentFlowProcess {

  private final DocumentRepository documentRepository;
  private final NumberGenerator numberGenerator;

  public DocumentFlowProcess(DocumentRepository documentRepository, NumberGenerator numberGenerator) {
    this.documentRepository = documentRepository;
    this.numberGenerator = numberGenerator;
  }

  public String create(CreateDocumentCommand cmd) {
    String number = numberGenerator.generate();
    Document document = new Document(number);
    documentRepository.save(document);
    return number;
  }

  public void edit(EditDocumentCommand cmd) {

  }

  public void verify(VerifyDocumentCommand cmd) {

  }

  public void publish(PublishDocumentCommand cmd) {

  }

  public void archive(ArchiveDocumentCommand cmd) {

  }

}
