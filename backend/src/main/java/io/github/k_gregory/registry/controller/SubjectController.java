package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.model.Subject;
import io.github.k_gregory.registry.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectRepository subjectRepo;

    @Autowired
    public SubjectController(SubjectRepository subjectRepo, ModelMapper mapper) {
        this.subjectRepo = subjectRepo;
    }

    @GetMapping
    @Transactional
    public List<Subject> all() {
        return subjectRepo.findAll();
    }

    @PostMapping
    @Transactional
    public Subject createSubject(@RequestBody Subject subject) {
        subject.setId(null);
        return subjectRepo.save(subject);
    }
}
