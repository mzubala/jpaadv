package pl.com.bottega.qma.docflow;

import java.util.UUID;

public class IsoNumberGenerator implements NumberGenerator {
  @Override
  public String generate() {
    return "ISO-" + UUID.randomUUID().toString();
  }
}
