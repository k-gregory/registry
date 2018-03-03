package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.IndividualDebtor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndividualDebtorRepository extends JpaRepository<IndividualDebtor, Long> {
    IndividualDebtor findByUid(String uid);
    List<IndividualDebtor> findByUidStartsWith(String uid);
}
