package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.user.RoleDto;

public interface RoleService {

  RoleDto getRole(Long id);

  List<RoleDto> getAllRoles();
}
