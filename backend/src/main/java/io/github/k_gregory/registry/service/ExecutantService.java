package io.github.k_gregory.registry.service;

import io.github.k_gregory.registry.dto.ExecutantCreateRequest;
import io.github.k_gregory.registry.dto.ExecutantDTO;
import io.github.k_gregory.registry.dto.ExecutantUpdateRequest;

import java.util.List;

public interface ExecutantService {
    List<ExecutantDTO> getAll();

    ExecutantDTO create(ExecutantCreateRequest request);

    ExecutantDTO update(Long id, ExecutantUpdateRequest request);
}
