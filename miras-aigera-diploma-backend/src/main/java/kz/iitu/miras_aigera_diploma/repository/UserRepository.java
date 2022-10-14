package kz.iitu.miras_aigera_diploma.repository;


import java.util.Optional;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);
  User findUserById(Long id);
  boolean existsByUsername(String username);
}
