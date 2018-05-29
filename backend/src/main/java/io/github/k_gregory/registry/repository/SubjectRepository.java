package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
