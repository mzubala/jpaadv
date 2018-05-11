package pl.com.bottega.qma.catalog;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DocumentDetails {

  @Id
  public String number;

  public String status;

  public Long creatorId;

  /*public String title, content;
  public Set<Long> editors;
  public Long verifierId, publisherId, archiverId;
  public Set<Long> confirmedBy;
  public Set<Long> pendingConfirmation;*/

}
