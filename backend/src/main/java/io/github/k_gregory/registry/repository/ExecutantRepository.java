package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Executant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExecutantRepository extends JpaRepository<Executant, Long> {
    @Query("select e from Executant e join fetch e.facility")
    List<Executant> fetchAllWithFacility();
}
