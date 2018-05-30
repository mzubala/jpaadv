package pl.com.bottega.qma.core.validation;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class InvalidCommandException extends RuntimeException {

  private Set<ConstraintViolation<Object>> errors;

  public InvalidCommandException(Set<ConstraintViolation<Object>> errors) {
    this.errors = errors;
  }

  public Set<ConstraintViolation<Object>> getErrors() {
    return errors;
  }

}
