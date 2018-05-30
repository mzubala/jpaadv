package pl.com.bottega.qma.docflow;

import java.util.UUID;

public class QepNumberGenerator implements NumberGenerator {
  @Override
  public String generate() {
    return "QEP-" + UUID.randomUUID().toString();
  }
}
