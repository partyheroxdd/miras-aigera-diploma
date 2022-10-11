package kz.iitu.miras_aigera_diploma.repository;

import kz.iitu.miras_aigera_diploma.model.entity.LostPeoplePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostPeopleRepository extends JpaRepository<LostPeoplePost, Long> {

}

