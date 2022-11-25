package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.RoleDto;

public interface RoleService {

  RoleDto saveRole(RoleDto roleDto);

  RoleDto getRole(Long id);

  void deleteRole(Long id);

  List<RoleDto> getAllRoles();
}
