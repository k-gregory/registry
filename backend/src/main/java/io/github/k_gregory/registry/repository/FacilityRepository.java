package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Facility;
import org.springframework.data.repository.CrudRepository;

public interface FacilityRepository extends CrudRepository<Facility, Long> {
}
