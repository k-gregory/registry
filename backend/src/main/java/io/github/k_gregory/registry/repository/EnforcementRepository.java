package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.dto.TopEnforcementDTO;
import io.github.k_gregory.registry.model.Enforcement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnforcementRepository extends JpaRepository<Enforcement, Long> {
    @Query("select new io.github.k_gregory.registry.dto.TopEnforcementDTO(" +
            "e.id, e.sender, e.receiver, f.name, e.startedAt, e.state" +
            ") from Enforcement e join e.facility f")
    List<TopEnforcementDTO> findTop(Pageable p);
}
