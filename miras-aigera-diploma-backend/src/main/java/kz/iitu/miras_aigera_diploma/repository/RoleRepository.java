package kz.iitu.miras_aigera_diploma.repository;

import kz.iitu.miras_aigera_diploma.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  public Role findByName(String name);
}
