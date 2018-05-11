package pl.com.bottega.qma.catalog;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DocumentDetails {

  @Id
  public String number;

  public String status;

  public Long creatorId;

  public String title, content;

  @ElementCollection
  public Set<Long> editors = new HashSet<>();

  /*public Long verifierId, publisherId, archiverId;
  public Set<Long> confirmedBy;
  public Set<Long> pendingConfirmation;*/

}
