package io.github.k_gregory.registry.service;

import io.github.k_gregory.registry.model.Facility;

import java.util.List;

public interface FacilityService {
    List<Facility> getAll();

    Facility create(String name);

    Facility rename(Long id, String name);
}
