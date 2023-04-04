package kz.iitu.miras_aigera_diploma.converter.district;

import kz.iitu.miras_aigera_diploma.model.dto.district.DistrictListInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.District;
import kz.iitu.miras_aigera_diploma.util.must_have.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class DistrictListInfoDtoConverter extends AbstractConverter<District, DistrictListInfoDto> {

  @Override
  public void fill(District source, DistrictListInfoDto target) {
    target.setId(source.getId());
    target.setName(source.getName());
  }
}
