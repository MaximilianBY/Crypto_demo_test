package by.cryptodemo.repositories;

import by.cryptodemo.model.Crypto;
import by.cryptodemo.model.User;
import by.cryptodemo.model.UserCrypto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCryptoRepository extends JpaRepository<UserCrypto, Long> {
  boolean existsByUserAndCrypto(User user, Crypto crypto);
  List<UserCrypto> getAllByCrypto(Crypto crypto);
}
