package kz.iitu.miras_aigera_diploma.converter.city;

import kz.iitu.miras_aigera_diploma.model.dto.city.CityListInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.City;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class CityListInfoDtoConverter extends AbstractConverter<City, CityListInfoDto> {

  @Override
  public void fill(City source, CityListInfoDto target) {
    target.setId(source.getId());
    target.setName(source.getName());
  }
}
