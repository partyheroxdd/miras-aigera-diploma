package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.entity.Role;

public interface RoleService {

  Role saveRole(Role role);

  Role getRole(Long id);

  void deleteRole(Long id);

  List<Role> getAllRoles();
}
