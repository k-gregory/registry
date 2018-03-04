package io.github.k_gregory.registry.service.impl;

import io.github.k_gregory.registry.dto.TopEnforcementDTO;
import io.github.k_gregory.registry.repository.EnforcementRepository;
import io.github.k_gregory.registry.service.EnforcementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnforcementServiceImpl implements EnforcementService {
    private final EnforcementRepository enforcements;
    private final ModelMapper mapper;

    @Autowired
    public EnforcementServiceImpl(EnforcementRepository enforcements, ModelMapper mapper) {
        this.enforcements = enforcements;
        this.mapper = mapper;
    }

    @Override
    public List<TopEnforcementDTO> listTopEnforcements() {
        return this.enforcements
                .fetchAllWithFacility(PageRequest.of(0, 10, Sort.by("id")))
                .stream()
                .map(e -> new TopEnforcementDTO(
                        e.getId(),
                        e.getSender(),
                        e.getReceiver(),
                        e.getFacility().getName(),
                        e.getStartedAt(),
                        e.getState()
                ))
                .collect(Collectors.toList());
    }
}
