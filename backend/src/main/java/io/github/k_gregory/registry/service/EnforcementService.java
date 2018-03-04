package io.github.k_gregory.registry.service;

import io.github.k_gregory.registry.model.Enforcement;

import java.util.List;

public interface EnforcementService {
    List<Enforcement> listTopEnforcements();
}
