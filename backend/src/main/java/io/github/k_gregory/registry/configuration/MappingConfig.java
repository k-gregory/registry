package io.github.k_gregory.registry.configuration;

import io.github.k_gregory.registry.dto.FacilityResponse;
import io.github.k_gregory.registry.dto.TopEnforcementDTO;
import io.github.k_gregory.registry.model.Enforcement;
import io.github.k_gregory.registry.model.Executant;
import io.github.k_gregory.registry.model.Facility;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        configureFacility(mapper);
        configureEnforcement(mapper);
        return mapper;
    }

    private void configureFacility(ModelMapper mapper) {
        mapper.addMappings(new PropertyMap<Facility, FacilityResponse>() {
            @Override
            protected void configure() {
                using((Converter<Executant, String>) context -> {
                    Executant head = context.getSource();

                    if(head == null)
                        return null;

                    return head.getFirstName() + " " + head.getMiddleName() + " " + head.getLastName();
                }).map(source.getHead()).setHeadName(null);
            }
        });
    }

    private void configureEnforcement(ModelMapper mapper) {
        TypeMap<Enforcement, TopEnforcementDTO> map = mapper.createTypeMap(Enforcement.class, TopEnforcementDTO.class);
        map.addMapping(e -> e.getSender().getName(), TopEnforcementDTO::setSender);
        map.addMapping(e -> e.getReceiver().getName(), TopEnforcementDTO::setReceiver);
    }
}
