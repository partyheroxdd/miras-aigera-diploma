package kz.iitu.miras_aigera_diploma.converter.role;

import kz.iitu.miras_aigera_diploma.model.dto.role.RoleListInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.Role;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class RoleListInfoDtoConverter extends AbstractConverter<Role, RoleListInfoDto> {

  @Override
  public void fill(Role source, RoleListInfoDto target) {
    target.setId(source.getId());
    target.setName(source.getName());
  }
}
