package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.dto.FacilityCreateReqeust;
import io.github.k_gregory.registry.dto.FacilityUpdateRequest;
import io.github.k_gregory.registry.model.Facility;
import io.github.k_gregory.registry.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facility")
public class FacilityController {
    private final FacilityService service;

    @Autowired
    public FacilityController(FacilityService service) {
        this.service = service;
    }

    @GetMapping
    public List<Facility> topEnforcements() {
        return service.getAll();
    }

    @PostMapping
    public Facility createFacility(@RequestBody FacilityCreateReqeust request) {
        return service.create(request.getName());
    }

    @PutMapping("{id}")
    public Facility updateFacility(@PathVariable Long id, @RequestBody FacilityUpdateRequest request) {
        return service.rename(id, request.getName());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFacility(@PathVariable Long id) {
        service.delete(id);
    }
}
