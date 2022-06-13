package com.hardwarestore.usecase.product;

import com.hardwarestore.dto.ProductDto;
import com.hardwarestore.mapper.ProductMapper;
import com.hardwarestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteProductUseCase {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper mapper;

    public Mono<Void> deleteProductById(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalStateException("Product with id:" + id + " doesn't exist")))
                .flatMap(product -> productRepository.deleteById(product.getId()));
    }
}
