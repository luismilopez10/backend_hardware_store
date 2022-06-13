package com.hardwarestore.usecase.product;

import com.hardwarestore.dto.ProductDto;
import com.hardwarestore.mapper.ProductMapper;
import com.hardwarestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PutProductUseCase {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper mapper;

    public Mono<ProductDto> putProduct(ProductDto productDto) {
        return productRepository.save(mapper.fromProductDtoToProduct(productDto)).map(mapper::fromProductToProductDto);
    }
}
