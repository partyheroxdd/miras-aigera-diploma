package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.city.CityListInfoDto;

public interface CityService {

  List<CityListInfoDto> findAll();
}
