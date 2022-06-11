package com.hardwarestore.usecase.provider;

import com.hardwarestore.dto.ProviderDto;
import com.hardwarestore.mapper.ProviderMapper;
import com.hardwarestore.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PostProviderUseCase {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderMapper mapper;

    public Mono<ProviderDto> postProvider(ProviderDto providerDto){
        return providerRepository.save(mapper.fromProviderDtoToProvider(providerDto)).map(mapper::fromProviderToProviderDto);
    }
}
