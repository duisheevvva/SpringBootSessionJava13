package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.entity.House;

import java.util.List;

public interface HouseRepository extends JpaRepository<House,Long> {

    @Query("select h from House h where h.agency.id = ?1")
    List<House> getAllHouse(Long agencyId);
    @Query("select h from House h where h.country ilike :word")
    House searchHouse(String word);


}
