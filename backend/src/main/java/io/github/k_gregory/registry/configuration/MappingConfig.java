package io.github.k_gregory.registry.configuration;

import io.github.k_gregory.registry.dto.TopEnforcementDTO;
import io.github.k_gregory.registry.model.Enforcement;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@Configuration
public class MappingConfig {
    private final Optional<List<MapperConfigurer>> mapperConfigurers;

    @Autowired
    public MappingConfig(Optional<List<MapperConfigurer>> mapperConfigurers) {
        this.mapperConfigurers = mapperConfigurers;
    }

    @FunctionalInterface
    public interface MapperConfigurer {
        void configure(ModelMapper mapper);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapperConfigurers
                .orElseGet(Collections::emptyList)
                .forEach(c -> c.configure(mapper));
        return mapper;
    }

    @Bean
    public static MapperConfigurer mapTopEnforcementDTO() {
        return (mapper) -> {
            TypeMap<Enforcement, TopEnforcementDTO> map = mapper.createTypeMap(
                    Enforcement.class,
                    TopEnforcementDTO.class
            );

            map.addMapping(
                    src -> src.getFacility().getName(),
                    TopEnforcementDTO::setFacilityName
            );
        };
    }

}
