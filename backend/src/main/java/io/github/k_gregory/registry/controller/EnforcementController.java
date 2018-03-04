package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.dto.TopEnforcementDTO;
import io.github.k_gregory.registry.service.EnforcementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enforcement")
public class EnforcementController {
    private final EnforcementService enforcement;

    @Autowired
    public EnforcementController(EnforcementService enforcement) {
        this.enforcement = enforcement;
    }

    @RequestMapping("/top")
    public List<TopEnforcementDTO> topEnforcements() {
        return enforcement.listTopEnforcements();
    }
}
