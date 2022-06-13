package com.hardwarestore.usecase.receipt;

import com.hardwarestore.collection.Product;
import com.hardwarestore.collection.Provider;
import com.hardwarestore.collection.Receipt;
import com.hardwarestore.dto.ReceiptDto;
import com.hardwarestore.mapper.ReceiptMapper;
import com.hardwarestore.repository.ReceiptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;

@SpringBootTest
class GetAllReceiptsUseCaseTest {

    private GetAllReceiptsUseCase useCase;

    @Autowired
    private ReceiptMapper mapper;

    @Mock
    ReceiptRepository repository;

    @BeforeEach
    void setUp() {
        useCase = new GetAllReceiptsUseCase(repository, mapper);
    }

    @Test
    void shouldGetAllReceipts() {
        Provider provider1 = new Provider();
        provider1.setId("provider1");

        Product product1 = new Product();
        product1.setId("product1");

        Receipt receipt1 = new Receipt();
        receipt1.setId("receipt1");
        receipt1.setDate(LocalDate.now());
        receipt1.setProductId(product1.getId());
        receipt1.setProductsAmount(5);
        receipt1.setProviderId(provider1.getId());

        Mockito.when(repository.findAll()).thenReturn(Flux.just(receipt1));
        Flux<ReceiptDto> receiptDtoFlux = useCase.getAllReceipts();

        StepVerifier.create(receiptDtoFlux)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(repository).findAll();
    }
}