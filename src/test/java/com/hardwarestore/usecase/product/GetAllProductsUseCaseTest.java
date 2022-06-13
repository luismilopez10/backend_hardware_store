package com.hardwarestore.usecase.product;

import com.hardwarestore.collection.Product;
import com.hardwarestore.collection.Provider;
import com.hardwarestore.dto.ProductDto;
import com.hardwarestore.mapper.ProductMapper;
import com.hardwarestore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class GetAllProductsUseCaseTest {

    private GetAllProductsUseCase useCase;

    @Autowired
    private ProductMapper mapper;

    @Mock
    ProductRepository repository;

    @BeforeEach
    void setUp() {
        useCase = new GetAllProductsUseCase(repository, mapper);
    }

    @Test
    void shouldGetAllProducts() {
        Provider provider1 = new Provider();
        provider1.setId("provider1");
        provider1.setName("Luis Miguel");
        provider1.setPassport("AO804867");

        Product product1 = new Product();
        product1.setId("product1");
        product1.setName("Screw");
        product1.setDescription("Black");
        product1.setStock(100);
        product1.setPrice(200.0);
        product1.setProviderId(provider1.getId());
        product1.setMinimumAmount(50);
        product1.setMaximumAmount(1000);

        Mockito.when(repository.findAll()).thenReturn(Flux.just(product1));
        Flux<ProductDto> productDTOFlux = useCase.getAllProducts();

        StepVerifier.create(productDTOFlux)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(repository).findAll();
    }
}