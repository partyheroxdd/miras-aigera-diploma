package kz.iitu.miras_aigera_diploma.converter.user;

import java.util.Objects;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserMeInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import kz.iitu.miras_aigera_diploma.util.JwtUtil;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserMeInfoDtoConverter extends AbstractConverter<User, UserMeInfoDto> {

  @Override
  public void fill(User source, UserMeInfoDto target) {
    target.setUsername(source.getUsername());
    target.setRole(JwtUtil.getRole());
    target.setFirstName(source.getFirstname());
    if (Objects.nonNull(source.getDistrict())) {
      target.setDistrict(source.getDistrict().getName());
    }
    target.setCity(source.getCity().getName());
  }
}
