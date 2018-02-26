package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.repository.FacilityRepository;
import io.github.k_gregory.registry.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/facility")
public class FacilityController {
    private final FacilityRepository facilities;
    private final SolutionRepository solutions;

    @Autowired
    public FacilityController(FacilityRepository facilities, SolutionRepository solutions) {
        this.facilities = facilities;
        this.solutions = solutions;
    }

    @GetMapping
    public String index(Model m){
        m.addAttribute("facilities", facilities.findAll());
        return "facility-list";
    }

    @GetMapping("/{id}")
    public String details(Model m, @PathVariable Long id){
        m.addAttribute("facility", facilities.findById(id).get());
        m.addAttribute("solutions", solutions.findAllByFacilityId(id));
        return "facility-details";
    }
}
