package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.district.DistrictListInfoDto;

public interface DistrictService {

  List<DistrictListInfoDto> findAllByCity(String city);
}
