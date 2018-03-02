package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
