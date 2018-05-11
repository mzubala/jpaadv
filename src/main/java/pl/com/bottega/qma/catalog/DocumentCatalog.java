package pl.com.bottega.qma.catalog;

import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Component;
import pl.com.bottega.qma.docflow.DocumentNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.QueryHint;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class DocumentCatalog {

  private final EntityManager entityManager;

  public DocumentCatalog(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public DocumentDetails get(String number) {
    DocumentDetails doc = entityManager.find(DocumentDetails.class, number);
    if (doc == null)
      throw new DocumentNotFoundException();
    return doc;
  }

  public DocumentSearchResults search(SearchDocumentsQuery query) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
    Root doc = criteriaQuery.from(DocumentDetails.class);
    criteriaQuery.select(criteriaBuilder.construct(DocumentBasicDetails.class,
        doc.get("number"), doc.get("title"), doc.get("status")
    ));
    Predicate predicate = criteriaBuilder.conjunction();
    if (query.query != null) {
      predicate = criteriaBuilder.and(predicate,
          criteriaBuilder.or(
              criteriaBuilder.like(doc.get("title"), "%" + query.query + "%"),
              criteriaBuilder.like(doc.get("content"), "%" + query.query + "%")
          )
      );
    }
    if (query.status != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(doc.get("status"), query.status));
    }
    criteriaQuery.where(predicate);
    Query q = entityManager.createQuery(criteriaQuery);
    q.setHint("javax.persistence.cache.retrieveMode", "USE");
    q.setHint("javax.persistence.cache.storeMode", "USE");
    List<DocumentBasicDetails> result = q.getResultList();
    return new DocumentSearchResults(result, 1, result.size(), result.size());
  }

}
