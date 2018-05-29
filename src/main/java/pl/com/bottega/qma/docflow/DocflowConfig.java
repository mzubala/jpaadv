package pl.com.bottega.qma.docflow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import java.util.UUID;

@Configuration
public class DocflowConfig {

  @Bean
  public DocumentFlowProcess documentFlowProcess(DocumentRepository documentRepository,
                                                 NumberGenerator numberGenerator

  ) {
    return new DocumentFlowProcess(documentRepository, numberGenerator);
  }

  @Bean
  public NumberGenerator numberGenerator() {
    return () -> UUID.randomUUID().toString();
  }

  @Bean
  public DocumentRepository documentRepository(EntityManager entityManager) {
    return new JpaDocumentRepository(entityManager);
  }

}
