package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Facility;
import io.github.k_gregory.registry.model.Solution;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacilityRepository extends CrudRepository<Facility, Long> {
}
