package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndividualRepository extends JpaRepository<Individual, Long> {
    Individual findByUid(String uid);
    List<Individual> findByUidStartsWith(String uid);
}
