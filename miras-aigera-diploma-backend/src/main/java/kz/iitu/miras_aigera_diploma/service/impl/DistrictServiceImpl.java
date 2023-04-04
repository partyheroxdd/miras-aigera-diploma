package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.List;
import kz.iitu.miras_aigera_diploma.converter.district.DistrictListInfoDtoConverter;
import kz.iitu.miras_aigera_diploma.model.dto.district.DistrictListInfoDto;
import kz.iitu.miras_aigera_diploma.repository.DistrictRepository;
import kz.iitu.miras_aigera_diploma.service.DistrictService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DistrictServiceImpl implements DistrictService {

  DistrictRepository districtRepository;

  DistrictListInfoDtoConverter districtListInfoDtoConverter;

  @Override
  @Transactional(readOnly = true)
  public List<DistrictListInfoDto> findAllByCity(String city) {
    return districtListInfoDtoConverter.convert(districtRepository.findAllByCityName(city));
  }
}
