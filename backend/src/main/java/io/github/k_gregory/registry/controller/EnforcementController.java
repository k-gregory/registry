package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.dto.TopEnforcementDTO;
import io.github.k_gregory.registry.model.Enforcement;
import io.github.k_gregory.registry.model.EnforcementState;
import io.github.k_gregory.registry.model.Facility;
import io.github.k_gregory.registry.repository.EnforcementRepository;
import io.github.k_gregory.registry.repository.FacilityRepository;
import io.github.k_gregory.registry.service.EnforcementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

class EnforcementDTO {
    private String facility;
    private String date;
    private String state;

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

@RestController
@RequestMapping("/api/enforcement")
public class EnforcementController {
    private final EnforcementService enforcement;
    private final FacilityRepository facility;
    private final EnforcementRepository er;

    @Autowired
    public EnforcementController(EnforcementService enforcement, FacilityRepository facility, EnforcementRepository er) {
        this.enforcement = enforcement;
        this.facility = facility;
        this.er = er;
    }

    @RequestMapping("/top")
    public List<TopEnforcementDTO> topEnforcements() {
        return enforcement.listTopEnforcements();
    }

    @PostMapping("/add")
    @ResponseBody
    @Transactional
    public String addEnforcement(@RequestBody EnforcementDTO body) {
        Enforcement newEnforcement = new Enforcement();
        Facility byName = facility.findByName(body.getFacility());
        if (byName == null) {
            byName = new Facility();
            byName.setName(body.getFacility());
            facility.save(byName);
        }
        newEnforcement.setFacility(byName);
        newEnforcement.setReceiver("Unknown");
        newEnforcement.setSender("Unk");
        newEnforcement.setState(EnforcementState.OPENED);
        newEnforcement.setStartedAt(new Date());
        er.save(newEnforcement);
        return "lala";
    }
}
