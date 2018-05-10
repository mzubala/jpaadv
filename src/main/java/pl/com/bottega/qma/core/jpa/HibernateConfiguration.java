package pl.com.bottega.qma.core.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class HibernateConfiguration implements HibernatePropertiesCustomizer {

  @Autowired
  private InjectingInterceptor injectingInterceptor;

  @Override
  public void customize(Map<String, Object> hibernateProperties) {
    hibernateProperties.put("hibernate.session_factory.interceptor", injectingInterceptor);
  }
}
