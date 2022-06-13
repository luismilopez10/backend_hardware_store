package com.hardwarestore.usecase.receipt;

import com.hardwarestore.collection.Provider;
import com.hardwarestore.dto.ProductDto;
import com.hardwarestore.dto.ReceiptDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

@SpringBootTest
class PostReceiptUseCaseTest {

    @MockBean
    PostReceiptUseCase useCase;

    @Test
    void shouldSaveReceipt() {
        Provider provider1 = new Provider();
        provider1.setId("provider1");

        ProductDto product1 = new ProductDto();
        product1.setId("product1");

        ReceiptDto receipt1 = new ReceiptDto();
        receipt1.setId("receipt1");
        receipt1.setDate(LocalDate.now());
        receipt1.setProductId(product1.getId());
        receipt1.setProductsAmount(5);
        receipt1.setProviderId(provider1.getId());

        StepVerifier.create(Mono.just(Mockito.when(useCase.postReceipt(receipt1))
                .thenReturn(Mono.just(receipt1)))).expectNextCount(1).expectComplete().verify();
    }
}