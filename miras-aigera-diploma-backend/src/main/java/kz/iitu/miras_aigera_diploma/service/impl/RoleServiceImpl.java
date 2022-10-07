package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.List;
import kz.iitu.miras_aigera_diploma.exceptions.RoleNameException;
import kz.iitu.miras_aigera_diploma.model.entity.Role;
import kz.iitu.miras_aigera_diploma.repository.RoleRepository;
import kz.iitu.miras_aigera_diploma.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public Role saveRole(Role role) {
    log.info("Saving role {}", role.getName());
    if (!role.getName().startsWith("ROLE_")) {
      throw new RoleNameException("User's role must be start with [ROLE_]");
    }
    return roleRepository.save(role);
  }

  @Override
  public Role getRole(Long id) {

    Role role = roleRepository.findById(id).orElse(null);
    if (role != null) {
      log.info("Getting role {} with id {}", role.getName(), id);
    }

    return role;
  }

  @Override
  public void deleteRole(Long id) {
    roleRepository.deleteById(id);
    log.info("Role with id {} deleted", id);
  }

  @Override
  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }
}
