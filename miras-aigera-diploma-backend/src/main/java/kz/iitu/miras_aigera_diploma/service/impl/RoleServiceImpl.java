package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.List;
import kz.iitu.miras_aigera_diploma.converter.RoleDtoConverter;
import kz.iitu.miras_aigera_diploma.exceptions.NotFoundException;
import kz.iitu.miras_aigera_diploma.exceptions.security.CustomSecurityException;
import kz.iitu.miras_aigera_diploma.model.Constants.ApiMessages;
import kz.iitu.miras_aigera_diploma.model.dto.RoleDto;
import kz.iitu.miras_aigera_diploma.model.entity.Role;
import kz.iitu.miras_aigera_diploma.repository.RoleRepository;
import kz.iitu.miras_aigera_diploma.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final RoleDtoConverter roleDtoConverter;

  @Override
  public RoleDto saveRole(RoleDto roleDto) {
    if (!roleDto.getName().startsWith("ROLE_")) {
      throw new CustomSecurityException(ApiMessages.INVALID_ROLE_NAME, HttpStatus.BAD_REQUEST);
    }
    Role role = Role.builder()
        .name(roleDto.getName()).build();
    roleRepository.save(role);
    log.info("Saving role {}", role);
    return roleDto;
  }

  @Override
  public RoleDto getRole(Long id) {
    Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(
        ApiMessages.ID_NOT_FOUND, HttpStatus.NOT_FOUND));
    log.info("Getting role {}", role);
    return roleDtoConverter.convert(role);
  }

  @Override
  public void deleteRole(Long id) {
    roleRepository.deleteById(id);
    log.info("Role with id {} deleted", id);
  }

  @Override
  public List<RoleDto> getAllRoles() {
    return roleDtoConverter.convert(roleRepository.findAll());
  }
}
