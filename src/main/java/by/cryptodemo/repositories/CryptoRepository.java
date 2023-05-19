package by.cryptodemo.repositories;

import by.cryptodemo.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, Long>,
    JpaSpecificationExecutor<Crypto> {
}
