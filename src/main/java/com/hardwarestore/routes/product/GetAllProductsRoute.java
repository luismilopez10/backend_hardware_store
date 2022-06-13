package com.hardwarestore.routes.product;

import com.hardwarestore.collection.Product;
import com.hardwarestore.dto.ProductDto;
import com.hardwarestore.usecase.product.GetAllProductsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetAllProductsRoute {

    @Bean
    @RouterOperation(
            path = "/api/v1/products/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllProductsUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "getAllProducts",
            operation = @Operation(
                    operationId = "getAllProducts",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = Product.class)))
                    }
            )
    )
    public RouterFunction<ServerResponse> getAllProducts(GetAllProductsUseCase getAllProductsUseCase) {
        return route(GET("/api/v1/products/"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(getAllProductsUseCase.getAllProducts(), ProductDto.class))
        );
    }
}
