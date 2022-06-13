package com.hardwarestore.routes.receipt;

import com.hardwarestore.collection.Provider;
import com.hardwarestore.dto.ReceiptDto;
import com.hardwarestore.usecase.receipt.GetAllReceiptsUseCase;
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
public class GetAllReceiptsRoute {

    @Bean
    @RouterOperation(
            path = "/api/v1/receipts/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllReceiptsUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "getAllReceipts",
            operation = @Operation(
                    operationId = "getAllReceipts",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation",
                                    content = @Content(schema = @Schema(implementation = Provider.class)))
                    }
            )
    )
    public RouterFunction<ServerResponse> getAllReceipts(GetAllReceiptsUseCase getAllReceiptsUseCase) {
        return route(GET("/api/v1/receipts/"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(getAllReceiptsUseCase.getAllReceipts(), ReceiptDto.class))
        );
    }
}
