package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Executant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutantRepository extends JpaRepository<Executant, Long> {
}
