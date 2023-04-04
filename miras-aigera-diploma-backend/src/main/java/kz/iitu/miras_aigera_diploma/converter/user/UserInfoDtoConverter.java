package kz.iitu.miras_aigera_diploma.converter.user;

import java.util.Objects;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDtoConverter extends AbstractConverter<User, UserInfoDto> {

  @Override
  public void fill(User source, UserInfoDto target) {
    target.setFirstname(source.getFirstname());
    target.setLastname(source.getLastname());
    if (Objects.nonNull(source.getMidname())) {
      target.setMidname(source.getMidname());
    }
    target.setIin(source.getUsername());
    if (Objects.nonNull(source.getPhoneNumber())) {
      target.setPhoneNumber(source.getPhoneNumber());
    }
    target.setCity(source.getCity().getName());
    if (Objects.nonNull(source.getAddress())) {
      target.setAddress(source.getAddress());
    }
  }
}
