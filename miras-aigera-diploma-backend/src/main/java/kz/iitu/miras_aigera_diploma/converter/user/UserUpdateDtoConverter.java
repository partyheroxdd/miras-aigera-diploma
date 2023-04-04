package kz.iitu.miras_aigera_diploma.converter.user;

import java.util.Objects;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserUpdateDto;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import kz.iitu.miras_aigera_diploma.repository.CityRepository;
import kz.iitu.miras_aigera_diploma.util.JwtUtil;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserUpdateDtoConverter extends AbstractConverter<UserUpdateDto, User> {

  CityRepository cityRepository;

  @Override
  public void fill(UserUpdateDto source, User target) {
    target.setUsername(JwtUtil.getUsername());
    target.setFirstname(source.getFirstname());
    target.setLastname(source.getLastname());
    if (Objects.nonNull(source.getMidname())) {
      target.setMidname(source.getMidname());
    }
    if (Objects.nonNull(source.getPhoneNumber())) {
      target.setPhoneNumber(source.getPhoneNumber());
    }
    target.setCity(cityRepository.findByName(source.getCity()));

    if (Objects.nonNull(source.getAddress())) {
      target.setAddress(source.getAddress());
    }

  }
}