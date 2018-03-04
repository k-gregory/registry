package io.github.k_gregory.registry.service;

import io.github.k_gregory.registry.dto.TopEnforcementDTO;

import java.util.List;

public interface EnforcementService {
    List<TopEnforcementDTO> listTopEnforcements();
}
