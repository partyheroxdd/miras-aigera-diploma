package kz.iitu.miras_aigera_diploma.model.dto.user;

import kz.iitu.miras_aigera_diploma.model.entity.Role;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoConverter extends AbstractConverter<Role, RoleDto> {

  @Override
  public void fill(Role source, RoleDto target) {
    target.setId(source.getId());
    target.setName(source.getName());
  }
}
