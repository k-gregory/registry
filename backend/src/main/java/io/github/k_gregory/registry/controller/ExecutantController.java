package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.dto.ExecutantCreateRequest;
import io.github.k_gregory.registry.dto.ExecutantDTO;
import io.github.k_gregory.registry.dto.ExecutantUpdateRequest;
import io.github.k_gregory.registry.service.ExecutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ExecutantDTO> createExecutant(@RequestBody ExecutantCreateRequest request) {
        ExecutantDTO dto = this.service.create(request);

        if(dto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ExecutantDTO>(dto, HttpStatus.OK);
    }

    @RequestMapping(path = "{id}",method = RequestMethod.PUT)
    public ResponseEntity<ExecutantDTO> updateExecutant(@PathVariable Long id, @RequestBody ExecutantUpdateRequest request) {
        ExecutantDTO dto = this.service.update(id, request);

        if(dto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ExecutantDTO>(dto, HttpStatus.OK);
    }
}
