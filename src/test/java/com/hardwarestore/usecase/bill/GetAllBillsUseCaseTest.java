package com.hardwarestore.usecase.bill;

import com.hardwarestore.collection.Bill;
import com.hardwarestore.collection.ProductSold;
import com.hardwarestore.dto.BillDto;
import com.hardwarestore.mapper.BillMapper;
import com.hardwarestore.repository.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class GetAllBillsUseCaseTest {

    private GetAllBillsUseCase useCase;

    @Autowired
    private BillMapper mapper;

    @Mock
    BillRepository repository;

    @BeforeEach
    void setUp() {
        useCase = new GetAllBillsUseCase(repository, mapper);
    }

    @Test
    void shouldGetAllBIll() {
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

        Bill bill1 = new Bill();
        bill1.setId("bill1");
        bill1.setDate(LocalDate.now());
        bill1.setClientName("Daniela");
        bill1.setEmployeeName("Raul");
        bill1.setProductsId(List.of(productSold1.getId(), productSold2.getId()));
        bill1.setTotalPrice(14500.0);

        Mockito.when(repository.findAll()).thenReturn(Flux.just(bill1));
        Flux<BillDto> billDtoFlux = useCase.getAllBills();

        StepVerifier.create(billDtoFlux)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(repository).findAll();
    }
}
