package pl.com.bottega.qma.core.validation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
@Aspect
public class ValidatingAspect {

  private final Validator validator;

  public ValidatingAspect() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Before("@within(pl.com.bottega.qma.core.validation.Validate) && args(command)")
  public void validate(Object command) {
    Set<ConstraintViolation<Object>> errors = validator.validate(command);
    if(!errors.isEmpty()) {
      throw new InvalidCommandException(errors);
    }
  }

}
