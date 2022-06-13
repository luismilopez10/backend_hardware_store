package com.hardwarestore.usecase.product;

import com.hardwarestore.dto.ProductDto;
import com.hardwarestore.mapper.ProductMapper;
import com.hardwarestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllProductsUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    public GetAllProductsUseCase(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll().map(mapper::fromProductToProductDto);
    }
}
