package com.hardwarestore.usecase.provider;

import com.hardwarestore.dto.ProviderDto;
import com.hardwarestore.mapper.ProviderMapper;
import com.hardwarestore.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllProvidersUseCase {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderMapper mapper;

    public Flux<ProviderDto> getAllProviders() {
        return providerRepository.findAll().map(mapper::fromProviderToProviderDto);
    }
}
