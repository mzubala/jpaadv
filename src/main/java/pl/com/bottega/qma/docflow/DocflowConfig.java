package pl.com.bottega.qma.docflow;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.bottega.qma.core.events.EventPublisher;
import pl.com.bottega.qma.core.events.SpringEventPublisher;

import javax.persistence.EntityManager;
import java.util.UUID;

@Configuration
public class DocflowConfig {

  @Bean
  public DocumentFlowProcess documentFlowProcess(DocumentRepository documentRepository,
                                                 NumberGenerator numberGenerator,
                                                 EventPublisher eventPublisher
                                                 ) {
    return new DocumentFlowProcess(documentRepository, numberGenerator, eventPublisher);
  }

  @Bean
  public NumberGenerator numberGenerator() {
    return () -> UUID.randomUUID().toString();
  }

  @Bean
  public DocumentRepository documentRepository(EntityManager entityManager) {
    return new JpaDocumentRepository(entityManager);
  }

  @Bean
  public EventPublisher eventPublisher(ApplicationContext ctx) {
    return new SpringEventPublisher(ctx);
  }

}
