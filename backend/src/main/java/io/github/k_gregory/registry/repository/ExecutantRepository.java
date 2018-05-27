package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Executant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExecutantRepository extends JpaRepository<Executant, Long> {
    @Query("select e from Executant e join fetch e.facility order by e.id")
    List<Executant> fetchAllWithFacility();

    @Query("select e from Executant e inner join e.facility f where e.id = :id and f.id = :facilityId")
    Optional<Executant> findExecutantInFacility(@Param("id") long id, @Param("facilityId") long facilityId);
}
