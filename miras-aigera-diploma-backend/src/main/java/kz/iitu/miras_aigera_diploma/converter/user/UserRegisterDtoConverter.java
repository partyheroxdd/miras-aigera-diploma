package kz.iitu.miras_aigera_diploma.converter.user;

import java.util.Objects;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserRegisterDto;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import kz.iitu.miras_aigera_diploma.repository.CityRepository;
import kz.iitu.miras_aigera_diploma.repository.DistrictRepository;
import kz.iitu.miras_aigera_diploma.repository.RoleRepository;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserRegisterDtoConverter extends AbstractConverter<UserRegisterDto, User> {

  CityRepository cityRepository;
  DistrictRepository districtRepository;
  RoleRepository roleRepository;

  PasswordEncoder passwordEncoder;

  @Override
  public void fill(UserRegisterDto source, User target) {
    target.setFirstname(source.getFirstname());
    target.setLastname(source.getLastname());
    if (Objects.nonNull(source.getMidname())) {
      target.setMidname(source.getMidname());
    }
    target.setUsername(source.getUsername());
    if (Objects.nonNull(source.getPhoneNumber())) {
      target.setPhoneNumber(source.getPhoneNumber());
    }
    target.setCity(cityRepository.findByName(source.getCity()));
    if (Objects.nonNull(source.getDistrict())) {
      target.setDistrict(districtRepository.findByName(source.getDistrict()));
    }
    if (Objects.nonNull(source.getAddress())) {
      target.setAddress(source.getAddress());
    }
    target.setPassword(passwordEncoder.encode(source.getPassword()));
    target.setRole(roleRepository.findByName(source.getRole()));
  }
}
