package io.github.k_gregory.registry.service.impl;

import io.github.k_gregory.registry.model.Facility;
import io.github.k_gregory.registry.repository.FacilityRepository;
import io.github.k_gregory.registry.service.FacilityService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityServiceImpl implements FacilityService {
    private final FacilityRepository repository;

    public FacilityServiceImpl(FacilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Facility> getAll() {
        return repository.findAll(Sort.by("id"));
    }

    @Transactional
    @Override
    public Facility create(String name) {
        Facility facility = new Facility();
        facility.setName(name);
        repository.save(facility);

        return facility;
    }

    @Transactional
    @Override
    public Facility rename(Long id, String name) {
        Optional<Facility> found = repository.findById(id);

        if(!found.isPresent())
            return null;

        Facility facility = found.get();
        facility.setName(name);
        repository.save(facility);

        return facility;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Optional<Facility> found = repository.findById(id);

        if(!found.isPresent())
            return;

        repository.delete(found.get());
    }
}
