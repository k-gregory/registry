package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Enforcement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnforcementRepository extends JpaRepository<Enforcement, Long> {
    @Query("select e from Enforcement e join fetch e.facility")
    List<Enforcement> fetchAllWithFacility(Pageable p);
}
