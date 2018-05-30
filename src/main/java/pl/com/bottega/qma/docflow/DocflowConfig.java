package pl.com.bottega.qma.docflow;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.bottega.qma.core.events.Event;
import pl.com.bottega.qma.core.events.EventPublisher;

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
  @ConditionalOnProperty(name = "qma.qualitySystem", havingValue = "ISO")
  public NumberGenerator isoNumberGenerator() {
    return new IsoNumberGenerator();
  }

  @Bean
  @ConditionalOnProperty(name = "qma.qualitySystem", havingValue = "QEP")
  public NumberGenerator qepNumberGenerator() {
    return new QepNumberGenerator();
  }

  @Bean
  public DocumentRepository documentRepository(EntityManager entityManager) {
    return new JpaDocumentRepository(entityManager);
  }

  @Bean
  public EventPublisher eventPublisher(ApplicationEventPublisher publisher) {
    return event -> {
      publisher.publishEvent(event);
    };
  }

}
