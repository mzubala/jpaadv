package pl.com.bottega.qma.catalog;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.com.bottega.qma.docflow.events.DocumentCreatedEvent;

@Component
public class DocflowListener {

  @EventListener
  public void onDocumentCreate(DocumentCreatedEvent event) {

  }

}
