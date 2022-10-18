package kz.iitu.miras_aigera_diploma.repository;

import kz.iitu.miras_aigera_diploma.model.entity.ForgotPasswordUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordUserRepository extends JpaRepository<ForgotPasswordUser, Long> {
  public ForgotPasswordUser findByResetCode(String resetCode);
}
