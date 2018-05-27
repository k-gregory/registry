package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    @Query("select f from Facility f left join fetch f.head order by f.id")
    List<Facility> fetchAllWithHead();

    @Query("select f from Facility f left join fetch f.head where f.id = :id")
    Optional<Facility> findByIdWithHead(@Param("id") long id);
}
