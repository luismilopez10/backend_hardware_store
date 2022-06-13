package com.hardwarestore.routes.product;

import com.hardwarestore.dto.ProductDto;
import com.hardwarestore.usecase.product.DeleteProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteProductRoute {

    @Bean
    @RouterOperation(
            path = "/api/v1/products/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            beanClass = DeleteProductUseCase.class,
            method = RequestMethod.DELETE,
            beanMethod = "deleteProductById",
            operation = @Operation(
                    operationId = "deleteProductById",
                    responses = {
                            @ApiResponse(
                                    responseCode = "202",
                                    description = "Successful operation"),
                            @ApiResponse(
                                    responseCode = "404",
                                    description = "Product not found")},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
            )
    )
    public RouterFunction<ServerResponse> deleteProductById(DeleteProductUseCase deleteProductUseCase) {
        return route(DELETE("/api/v1/products/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> deleteProductUseCase.deleteProductById(request.pathVariable("id"))
                        .flatMap(_e -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }
}
