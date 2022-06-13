package com.hardwarestore.usecase.provider;

import com.hardwarestore.collection.Provider;
import com.hardwarestore.dto.ProviderDto;
import com.hardwarestore.mapper.ProviderMapper;
import com.hardwarestore.repository.ProviderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class GetAllProvidersUseCaseTest {

    private GetAllProvidersUseCase useCase;

    @Autowired
    private ProviderMapper mapper;

    @Mock
    ProviderRepository repository;

    @BeforeEach
    void setUp() {
        useCase = new GetAllProvidersUseCase(repository, mapper);
    }

    @Test
    void shouldGetAllProviders() {
        Provider provider1 = new Provider();
        provider1.setId("provider1");
        provider1.setName("Luis Miguel");
        provider1.setPassport("AO804867");

        Provider provider2 = new Provider();
        provider2.setId("provider2");
        provider2.setName("Daniela");
        provider2.setPassport("BW946873");

        Mockito.when(repository.findAll()).thenReturn(Flux.just(provider1, provider2));
        Flux<ProviderDto> providerDtoFlux = useCase.getAllProviders();

        StepVerifier.create(providerDtoFlux)
                .expectNextCount(2)
                .verifyComplete();

        Mockito.verify(repository).findAll();
    }
}