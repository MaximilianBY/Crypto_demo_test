package by.cryptodemo.repositories;

import by.cryptodemo.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByName(String userName);
  Optional<User> getUserByName(String userName);
}
