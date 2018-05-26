package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.dto.ExecutantCreateRequest;
import io.github.k_gregory.registry.dto.ExecutantDTO;
import io.github.k_gregory.registry.dto.ExecutantUpdateRequest;
import io.github.k_gregory.registry.service.ExecutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/executant")
public class ExecutantController {
    private final ExecutantService service;

    @Autowired
    public ExecutantController(ExecutantService service) {
        this.service = service;
    }

    @GetMapping
    public List<ExecutantDTO> getAllExecutants() {
        return this.service.getAll();
    }

    @PostMapping()
    public ExecutantDTO createExecutant(@RequestBody ExecutantCreateRequest request) {
        return this.service.create(request);
    }

    @PutMapping("{id}")
    public ExecutantDTO updateExecutant(@PathVariable Long id, @RequestBody ExecutantUpdateRequest request) {
        return this.service.update(id, request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFacility(@PathVariable Long id) {
        service.delete(id);
    }
}
