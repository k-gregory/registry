package io.github.k_gregory.registry.configuration;

import io.github.k_gregory.registry.dto.TopEnforcementDTO;
import io.github.k_gregory.registry.model.Enforcement;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    public void mapTopEnforcementDTO(ModelMapper mapper){
        TypeMap<Enforcement, TopEnforcementDTO> map = mapper.createTypeMap(Enforcement.class, TopEnforcementDTO.class);
        map.addMapping(src->src.getFacility().getName(), TopEnforcementDTO::setFacilityName);
    }
}
