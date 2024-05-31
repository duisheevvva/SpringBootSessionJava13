package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.entity.Agency;

import java.util.List;

public interface AgencyRepository extends JpaRepository<Agency,Long> {

    @Query("select a from Agency  a where a.name ilike :word")
    List<Agency> searchAgency(@Param("word") String word );

}
