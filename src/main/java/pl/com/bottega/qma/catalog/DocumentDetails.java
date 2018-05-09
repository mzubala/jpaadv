package pl.com.bottega.qma.catalog;

import java.util.Set;

public class DocumentDetails {

  public String number, title, content, status;
  public Set<Long> editors;
  public Long verifierId, publisherId, archiverId;
  public Set<Long> confirmedBy;
  public Set<Long> pendingConfirmation;

}
