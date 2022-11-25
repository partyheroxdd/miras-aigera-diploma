package kz.iitu.miras_aigera_diploma.converter;

import kz.iitu.miras_aigera_diploma.model.dto.UserMeInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserMeInfoDtoConverter extends AbstractConverter<User, UserMeInfoDto> {
  @Override
  public void fill(User source, UserMeInfoDto target) {
    target.setId(source.getId());
    target.setUsername(source.getUsername());
    target.setRoleName(source.getRoles().stream().findFirst().get().getName());
  }
}
