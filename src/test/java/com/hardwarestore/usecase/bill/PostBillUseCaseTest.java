package com.hardwarestore.usecase.bill;

import com.hardwarestore.collection.BillProducts;
import com.hardwarestore.collection.ProductSold;
import com.hardwarestore.dto.BillDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class PostBillUseCaseTest {

    @MockBean
    PostBillUseCase useCase;

    @Test
    void shouldSaveBill() {
        ProductSold productSold1 = new ProductSold();
        productSold1.setId("product1");
        productSold1.setName("Screw");
        productSold1.setPrice(200.0);
        productSold1.setAmount(10);

        ProductSold productSold2 = new ProductSold();
        productSold2.setId("product2");
        productSold2.setName("Pliers");
        productSold2.setPrice(12500.0);
        productSold2.setAmount(1);

        BillDto bill1 = new BillDto();
        bill1.setId("bill1");
        bill1.setDate(LocalDate.now());
        bill1.setClientName("Daniela");
        bill1.setEmployeeName("Raul");
        List<BillProducts> products = new ArrayList<>();
        products.add(new BillProducts(productSold1.getId(),productSold1.getName(),productSold1.getPrice(), productSold1.getAmount()));
        products.add(new BillProducts(productSold2.getId(),productSold2.getName(),productSold2.getPrice(), productSold2.getAmount()));
        bill1.setProducts(products);
        bill1.setTotalPrice(14500.0);

        StepVerifier.create(Mono.just(Mockito.when(useCase.postBill(bill1))
                .thenReturn(Mono.just(bill1)))).expectNextCount(1).expectComplete().verify();
    }
}