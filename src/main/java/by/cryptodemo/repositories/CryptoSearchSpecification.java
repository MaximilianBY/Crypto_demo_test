package by.cryptodemo.repositories;

import by.cryptodemo.model.Crypto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

@Slf4j
@AllArgsConstructor
public class CryptoSearchSpecification implements Specification<Crypto> {

  private String crypto;

  @Override
  public Predicate toPredicate(Root<Crypto> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicates = new ArrayList<>();

    if (Optional.ofNullable(crypto).isPresent()
        && !crypto.isBlank()) {
      predicates.add(criteriaBuilder.or(
          criteriaBuilder.like(root.get("name"), "%" + crypto + "%"),
          criteriaBuilder.like(root.get("nameId"), "%" + crypto + "%"),
          criteriaBuilder.like(root.get("symbol"), "%" + crypto + "%")
      ));


    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }
}
