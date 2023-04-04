package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.List;
import kz.iitu.miras_aigera_diploma.converter.city.CityListInfoDtoConverter;
import kz.iitu.miras_aigera_diploma.model.dto.city.CityListInfoDto;
import kz.iitu.miras_aigera_diploma.repository.CityRepository;
import kz.iitu.miras_aigera_diploma.service.CityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CityServiceImpl implements CityService {

  CityRepository cityRepository;

  CityListInfoDtoConverter cityListInfoDtoConverter;

  @Override
  @Transactional(readOnly = true)
  public List<CityListInfoDto> findAll() {
    return cityListInfoDtoConverter.convert(cityRepository.findAll());
  }
}
