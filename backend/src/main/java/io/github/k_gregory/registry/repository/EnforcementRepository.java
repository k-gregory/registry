package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Enforcement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnforcementRepository extends CrudRepository<Enforcement, Long> {
    public List<Enforcement> findAllByFacilityId(Long id);
}
