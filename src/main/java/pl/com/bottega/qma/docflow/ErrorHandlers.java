package pl.com.bottega.qma.docflow;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.com.bottega.qma.core.validation.InvalidCommandException;

import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlers {
  @ExceptionHandler(DocumentNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void handleDocumentNotFound() {
  }

  @ExceptionHandler(InvalidDocumentOperation.class)
  @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
  public void handleInvalidDocumentOp() {
  }

  @ExceptionHandler(InvalidCommandException.class)
  public ResponseEntity<?> handleInvalidCommand(InvalidCommandException ex) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
    return new ResponseEntity(
        ex.getErrors().stream().map((e) -> e.getMessage()).collect(Collectors.toSet()),
        headers,
        HttpStatus.UNPROCESSABLE_ENTITY
    );
  }
}
