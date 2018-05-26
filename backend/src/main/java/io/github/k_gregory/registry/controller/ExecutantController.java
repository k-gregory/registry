package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.dto.ExecutantDTO;
import io.github.k_gregory.registry.model.Executant;
import io.github.k_gregory.registry.repository.ExecutantRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/executant")
public class ExecutantController {
    private final ExecutantRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ExecutantController(ExecutantRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ExecutantDTO> topEnforcements() {
        List<Executant> executants = repository.fetchAllWithFacility();
        Type type = new TypeToken<List<ExecutantDTO>>() {}.getType();
        return mapper.map(executants, type);
    }
}
