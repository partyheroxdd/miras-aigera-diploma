package kz.iitu.miras_aigera_diploma.repository;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

  District findByName(String name);

  List<District> findAllByCityName(String cityName);
}
