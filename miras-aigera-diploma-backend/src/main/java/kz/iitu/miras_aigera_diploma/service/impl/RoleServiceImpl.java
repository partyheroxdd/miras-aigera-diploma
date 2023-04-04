package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.List;
import kz.iitu.miras_aigera_diploma.converter.role.RoleListInfoDtoConverter;
import kz.iitu.miras_aigera_diploma.model.dto.role.RoleListInfoDto;
import kz.iitu.miras_aigera_diploma.repository.RoleRepository;
import kz.iitu.miras_aigera_diploma.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoleServiceImpl implements RoleService {

  RoleRepository roleRepository;

  RoleListInfoDtoConverter roleListInfoDtoConverter;

  @Override
  @Transactional(readOnly = true)
  public List<RoleListInfoDto> findAll() {
    return roleListInfoDtoConverter.convert(roleRepository.findAll());
  }
}
