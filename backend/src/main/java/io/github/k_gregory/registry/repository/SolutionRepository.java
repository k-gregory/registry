package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Solution;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolutionRepository extends CrudRepository<Solution, Long> {
    public List<Solution> findAllByFacilityId(Long id);
}
