package kz.iitu.miras_aigera_diploma.repository;

import kz.iitu.miras_aigera_diploma.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

  City findByName(String name);
}
