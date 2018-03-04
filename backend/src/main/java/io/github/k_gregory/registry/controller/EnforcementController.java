package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.model.Enforcement;
import io.github.k_gregory.registry.service.EnforcementService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

class TopEnforcementDTO {
    private Long id;
    private String sender;
    private String receiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}

@RestController
@RequestMapping("/api/enforcement")
public class EnforcementController {
    private final ModelMapper mapper;
    private final EnforcementService enforcement;

    @Autowired
    public EnforcementController(ModelMapper mapper, EnforcementService enforcement) {
        this.mapper = mapper;
        this.enforcement = enforcement;
    }

    @RequestMapping("/top")
    public List<TopEnforcementDTO> topEnforcements() {
        List<Enforcement> topEnforcements = enforcement.listTopEnforcements();
        Type type = new TypeToken<List<TopEnforcementDTO>>() {
        }.getType();
        return mapper.map(topEnforcements, type);
    }
}
