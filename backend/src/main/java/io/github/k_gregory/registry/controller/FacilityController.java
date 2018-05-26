package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.model.Facility;
import io.github.k_gregory.registry.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/facility")
public class FacilityController {
    private final FacilityRepository repository;

    @Autowired
    public FacilityController(FacilityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Facility> topEnforcements() {
        return repository.findAll();
    }
}
