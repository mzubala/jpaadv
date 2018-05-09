package pl.com.bottega.qma.docflow.commands;


import java.util.HashSet;
import java.util.Set;

public class PublishDocumentCommand {

  public String documentNumber;
  public Set<String> departmentCodes = new HashSet<>();
  public Long publisherId;

}
