package pl.com.bottega.qma.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.qma.catalog.DocumentDetails;

import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class DocflowTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void createsDocument() {
    ResponseEntity<Map> postResponse = restTemplate.postForEntity("/documents", null, Map.class);
    String number = (String) postResponse.getBody().get("number");
    ResponseEntity<DocumentDetails> getResponse = restTemplate.getForEntity("/documents/{number}", DocumentDetails.class, number);
    DocumentDetails documentDetails = getResponse.getBody();


    assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(documentDetails.number).isEqualTo(number);
    assertThat(documentDetails.status).isEqualTo("DRAFT");
    assertThat(documentDetails.creatorId).isEqualTo(1L);
  }


}
