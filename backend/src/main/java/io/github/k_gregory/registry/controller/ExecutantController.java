package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.model.Executant;
import io.github.k_gregory.registry.repository.ExecutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/executant")
public class ExecutantController {
    private final ExecutantRepository repository;

    @Autowired
    public ExecutantController(ExecutantRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Executant> topEnforcements() {
        return repository.findAll();
    }
}
