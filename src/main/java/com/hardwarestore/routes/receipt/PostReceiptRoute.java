package com.hardwarestore.routes.receipt;

import com.hardwarestore.collection.Receipt;
import com.hardwarestore.dto.ReceiptDto;
import com.hardwarestore.usecase.receipt.PostReceiptUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PostReceiptRoute {

    @Bean
    @RouterOperation(
            path = "/api/v1/receipts/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            beanClass = PostReceiptUseCase.class,
            method = RequestMethod.POST,
            beanMethod = "createReceipt",
            operation = @Operation(
                    operationId = "createReceipt",
                    responses = {
                            @ApiResponse(responseCode = "201", description = "Successful operation",
                                    content = @Content(schema = @Schema(implementation = Receipt.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid recipe details supplied")},
                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Receipt.class)))
            ))
    public RouterFunction<ServerResponse> createReceipt(PostReceiptUseCase postReceiptUseCase) {
        return route(POST("/api/v1/receipts/").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ReceiptDto.class)
                        .flatMap(postReceiptUseCase::postReceipt)
                        .flatMap(dto -> ServerResponse.status(HttpStatus.CREATED)
                                .bodyValue(dto)
                        )
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }
}
