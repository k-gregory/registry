package io.github.k_gregory.registry.service;

import io.github.k_gregory.registry.dto.FacilityResponse;
import io.github.k_gregory.registry.dto.FacilityUpdateRequest;

import java.util.List;

public interface FacilityService {
    List<FacilityResponse> getAll();

    FacilityResponse create(String name);

    FacilityResponse update(Long id, FacilityUpdateRequest request);

    void delete(Long id);
}
