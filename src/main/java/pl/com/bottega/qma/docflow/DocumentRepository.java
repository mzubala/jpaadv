package pl.com.bottega.qma.docflow;

public interface DocumentRepository {

  void save(Document document);

  Document get(String documentNumber) ;
}
