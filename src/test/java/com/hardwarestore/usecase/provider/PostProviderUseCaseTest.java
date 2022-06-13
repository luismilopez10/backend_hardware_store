package com.hardwarestore.usecase.provider;

import com.hardwarestore.dto.ProviderDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class PostProviderUseCaseTest {

    @MockBean
    PostProviderUseCase useCase;

    @Test
    void shouldSaveProvider() {
        ProviderDto provider1 = new ProviderDto();
        provider1.setId("provider1");
        provider1.setName("Luis Miguel");
        provider1.setPassport("AO804867");

        StepVerifier.create(Mono.just(Mockito.when(useCase.postProvider(provider1))
                .thenReturn(Mono.just(provider1)))).expectNextCount(1).expectComplete().verify();
    }
}