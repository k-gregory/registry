package io.github.k_gregory.registry.configuration;

import io.github.k_gregory.registry.dto.FacilityResponse;
import io.github.k_gregory.registry.model.Executant;
import io.github.k_gregory.registry.model.Facility;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        configureFacility(mapper);
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
}
