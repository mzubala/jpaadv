package pl.com.bottega.qma.docflow.commands;

import javax.validation.constraints.NotNull;

public class ArchiveDocumentCommand {

  @NotNull
  public String documentNumber;

  @NotNull
  public Long archiverId;

}
