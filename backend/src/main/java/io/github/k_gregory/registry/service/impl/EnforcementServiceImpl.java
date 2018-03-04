package io.github.k_gregory.registry.service.impl;

import io.github.k_gregory.registry.dto.TopEnforcementDTO;
import io.github.k_gregory.registry.repository.EnforcementRepository;
import io.github.k_gregory.registry.service.EnforcementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnforcementServiceImpl implements EnforcementService {
    private final EnforcementRepository enforcements;

    @Autowired
    public EnforcementServiceImpl(EnforcementRepository enforcements) {
        this.enforcements = enforcements;
    }

    @Override
    public List<TopEnforcementDTO> listTopEnforcements() {
        return enforcements.findTop(PageRequest.of(0, 10, Sort.by("id")));
    }
}
