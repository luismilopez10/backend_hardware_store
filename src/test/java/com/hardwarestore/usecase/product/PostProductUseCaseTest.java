package com.hardwarestore.usecase.product;

import com.hardwarestore.collection.Provider;
import com.hardwarestore.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class PostProductUseCaseTest {
    @MockBean
    PostProductUseCase useCase;

    @Test
    void shouldSaveProduct() {
        Provider provider1 = new Provider();
        provider1.setId("provider1");
        provider1.setName("Luis Miguel");
        provider1.setPassport("AO804867");

        ProductDto product1 = new ProductDto();
        product1.setId("product1");
        product1.setName("Screw");
        product1.setDescription("Black");
        product1.setStock(100);
        product1.setPrice(200.0);
        product1.setProviderId(provider1.getId());
        product1.setMinimumAmount(50);
        product1.setMaximumAmount(1000);

        StepVerifier.create(Mono.just(Mockito.when(useCase.postProduct(product1))
                .thenReturn(Mono.just(product1)))).expectNextCount(1).expectComplete().verify();


    }
}