package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.security.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<Group, Long> {
    Optional<Group> getByName(String name);
}
