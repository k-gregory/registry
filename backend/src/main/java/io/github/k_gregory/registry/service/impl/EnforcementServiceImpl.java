package io.github.k_gregory.registry.service.impl;

import io.github.k_gregory.registry.dto.TopEnforcementDTO;
import io.github.k_gregory.registry.model.Enforcement;
import io.github.k_gregory.registry.repository.EnforcementRepository;
import io.github.k_gregory.registry.service.EnforcementService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

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
        Type type = new TypeToken<List<TopEnforcementDTO>>() {}.getType();
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        List<Enforcement> topEnforcements = enforcements
                .fetchAllWithFacility(pageRequest);
        return mapper.map(topEnforcements, type);
    }
}
