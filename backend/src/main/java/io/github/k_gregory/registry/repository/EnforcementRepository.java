package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Enforcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnforcementRepository extends JpaRepository<Enforcement, Long> {
    List<Enforcement> findAllByFacilityId(Long id);
}
