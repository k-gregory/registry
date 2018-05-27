package io.github.k_gregory.registry.service.impl;

import io.github.k_gregory.registry.dto.ExecutantCreateRequest;
import io.github.k_gregory.registry.dto.ExecutantDTO;
import io.github.k_gregory.registry.dto.ExecutantUpdateRequest;
import io.github.k_gregory.registry.infrastructure.RegistryApplicationException;
import io.github.k_gregory.registry.infrastructure.ResourceNotFoundException;
import io.github.k_gregory.registry.model.Executant;
import io.github.k_gregory.registry.model.Facility;
import io.github.k_gregory.registry.repository.ExecutantRepository;
import io.github.k_gregory.registry.repository.FacilityRepository;
import io.github.k_gregory.registry.service.ExecutantService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class ExecutantServiceImpl implements ExecutantService {
    private final ExecutantRepository repository;
    private FacilityRepository facilityRepository;
    private final ModelMapper mapper;

    @Autowired
    public ExecutantServiceImpl(ExecutantRepository repository,
                                FacilityRepository facilityRepository,
                                ModelMapper mapper) {
        this.repository = repository;
        this.facilityRepository = facilityRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ExecutantDTO> getAll() {
        List<Executant> executants = repository.fetchAllWithFacility();
        Type type = new TypeToken<List<ExecutantDTO>>() {}.getType();
        return mapper.map(executants, type);    }

    @Transactional
    @Override
    public ExecutantDTO create(ExecutantCreateRequest request) {
        Optional<Facility> found = facilityRepository.findById(request.getFacilityId());

        Facility facility = RegistryApplicationException.checked(found, "Could not create executant. Provided facility does not exist.");
        Executant executant = new Executant();
        executant.setFirstName(request.getFirstName());
        executant.setMiddleName(request.getMiddleName());
        executant.setLastName(request.getLastName());
        executant.setPhoneNumber(request.getPhoneNumber());
        executant.setFacility(facility);

        repository.save(executant);
        return mapper.map(executant, ExecutantDTO.class);
    }

    @Transactional
    @Override
    public ExecutantDTO update(Long id, ExecutantUpdateRequest request) {
        Optional<Executant> found = repository.findById(id);

        Executant executant = ResourceNotFoundException.checked(found);
        executant.setFirstName(request.getFirstName());
        executant.setMiddleName(request.getMiddleName());
        executant.setLastName(request.getLastName());

        repository.save(executant);
        return mapper.map(executant, ExecutantDTO.class);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Optional<Executant> found = repository.findById(id);

        if(!found.isPresent())
            return;

        repository.delete(found.get());
    }
}
