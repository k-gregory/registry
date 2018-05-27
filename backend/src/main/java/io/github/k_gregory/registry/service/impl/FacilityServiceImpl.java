package io.github.k_gregory.registry.service.impl;

import io.github.k_gregory.registry.dto.FacilityResponse;
import io.github.k_gregory.registry.dto.FacilityUpdateRequest;
import io.github.k_gregory.registry.infrastructure.RegistryApplicationException;
import io.github.k_gregory.registry.infrastructure.ResourceNotFoundException;
import io.github.k_gregory.registry.model.Executant;
import io.github.k_gregory.registry.model.Facility;
import io.github.k_gregory.registry.repository.ExecutantRepository;
import io.github.k_gregory.registry.repository.FacilityRepository;
import io.github.k_gregory.registry.service.FacilityService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityServiceImpl implements FacilityService {
    private final FacilityRepository repository;
    private final ExecutantRepository executantRepository;
    private final ModelMapper mapper;

    public FacilityServiceImpl(FacilityRepository repository,
                               ExecutantRepository executantRepository,
                               ModelMapper mapper) {
        this.repository = repository;
        this.executantRepository = executantRepository;
        this.mapper = mapper;
    }

    @Override
    public List<FacilityResponse> getAll() {
        List<Facility> facilities = repository.fetchAllWithHead();
        Type type = new TypeToken<List<FacilityResponse>>() {}.getType();
        return mapper.map(facilities, type);
    }

    @Transactional
    @Override
    public FacilityResponse create(String name) {
        Facility facility = new Facility();
        facility.setName(name);
        repository.save(facility);

        return mapper.map(facility, FacilityResponse.class);
    }

    @Transactional
    @Override
    public FacilityResponse update(Long id, FacilityUpdateRequest request) {
        Optional<Facility> found = repository.findByIdWithHead(id);

        Facility facility = ResourceNotFoundException.checked(found);
        facility.setName(request.getName());

        if(request.getFacilityHeadId() != null) {
            Optional<Executant> foundExecutant = executantRepository
                    .findExecutantInFacility(request.getFacilityHeadId(), id);

            Executant executant = RegistryApplicationException
                    .checked(foundExecutant, "Could not update facility. Executant was not found");

            facility.setHead(executant);
        } else {
            facility.setHead(null);
        }

        repository.save(facility);

        return mapper.map(facility, FacilityResponse.class);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Optional<Facility> found = repository.findByIdWithHead(id);

        if(!found.isPresent())
            return;

        repository.delete(found.get());
    }
}
