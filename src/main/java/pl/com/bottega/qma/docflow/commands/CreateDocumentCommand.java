package pl.com.bottega.qma.docflow.commands;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateDocumentCommand {

  @NotNull
  public Long creatorId;

}
