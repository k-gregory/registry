package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.security.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> getByName(String name);
}
