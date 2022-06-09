package com.hardwarestore.mapper;

import com.hardwarestore.collection.Provider;
import com.hardwarestore.dto.ProviderDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class ProviderMapper {

    private final ModelMapper mapper;

    public ProviderMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProviderDto fromProviderToProviderDto(Provider Provider){
        return mapper.map(Provider, ProviderDto.class);
    }

    public Provider fromProviderDtoToProvider(ProviderDto ProviderDto){
        return mapper.map(ProviderDto, Provider.class);
    }
}