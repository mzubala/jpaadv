package pl.com.bottega.qma.docflow;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.qma.docflow.commands.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/documents")
public class DocumentController {

  private DocumentFlowProcess documentFlowProcess;

  public DocumentController(DocumentFlowProcess documentFlowProcess) {
    this.documentFlowProcess = documentFlowProcess;
  }

  @PostMapping
  public Map<String, String> create() {
    CreateDocumentCommand command = new CreateDocumentCommand();
    command.creatorId = 1L;
    String number = documentFlowProcess.create(command);
    Map<String, String> numberMap = new HashMap<>();
    numberMap.put("number", number);
    return numberMap;
  }

  // PUT /documents/123
  @PutMapping("/{number}")
  public void update(@PathVariable String number, @RequestBody EditDocumentCommand command) {
    command.editorId = 1L;
    command.documentNumber = number;
    documentFlowProcess.edit(command);
  }

  @PatchMapping("/{number}/verification")
  public void verify(@PathVariable String number) {
    VerifyDocumentCommand verifyDocumentCommand = new VerifyDocumentCommand();
    verifyDocumentCommand.documentNumber = number;
    verifyDocumentCommand.verifierId = 1L;
    documentFlowProcess.verify(verifyDocumentCommand);
  }

  @PatchMapping("/{number}/publication")
  public void publish(@RequestBody PublishDocumentCommand cmd, @PathVariable String number) {
    cmd.documentNumber = number;
    cmd.publisherId = 1L;
    documentFlowProcess.publish(cmd);
  }

  @DeleteMapping("/{number}")
  public void archive(@PathVariable String number) {
    ArchiveDocumentCommand cmd = new ArchiveDocumentCommand();
    cmd.archiverId = 1L;
    cmd.documentNumber = number;
    documentFlowProcess.archive(cmd);
  }

}
