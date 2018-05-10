package pl.com.bottega.qma.core.events;

import org.springframework.context.ApplicationContext;

public class SpringEventPublisher implements EventPublisher {

  private final ApplicationContext ctx;

  public SpringEventPublisher(ApplicationContext ctx) {
    this.ctx = ctx;
  }

  @Override
  public void publish(Event event) {
    ctx.publishEvent(event);
  }
}
