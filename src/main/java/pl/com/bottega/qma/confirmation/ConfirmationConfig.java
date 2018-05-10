package pl.com.bottega.qma.confirmation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.bottega.qma.core.events.EventPublisher;
import pl.com.bottega.qma.hr.HrFacade;

import javax.persistence.EntityManager;

@Configuration
public class ConfirmationConfig {

  @Bean
  public DocumentPublishedListener documentPublishedListener(DocumentConfirmationProcess confirmationProcess) {
    return new DocumentPublishedListener(confirmationProcess);
  }

  @Bean
  public DocumentConfirmationProcess documentConfirmationProcess(ConfirmationRepository confirmationRepository,
                                                                 HrFacade hrFacade, EventPublisher eventPublisher
  ) {
    return new DocumentConfirmationProcess(confirmationRepository, hrFacade, eventPublisher);
  }

  @Bean
  public ConfirmationRepository confirmationRepository(EntityManager entityManager) {
    return new JpaConfirmationRepository(entityManager);
  }

}
