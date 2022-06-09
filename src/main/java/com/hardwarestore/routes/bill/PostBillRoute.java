package com.hardwarestore.routes.bill;

import com.hardwarestore.collection.Bill;
import com.hardwarestore.dto.BillDto;
import com.hardwarestore.usecase.bill.PostBillUseCase;
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
public class PostBillRoute {

    @Bean
    @RouterOperation(path = "/api/v1/bills/", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = PostBillUseCase.class,
            method = RequestMethod.POST,
            beanMethod = "postBill",
            operation = @Operation(
                    operationId = "postBill",
                    responses = {
                            @ApiResponse(responseCode = "201", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = Bill.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid recipe details supplied")},
                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Bill.class)))
            )
    )
    public RouterFunction<ServerResponse> createBill(PostBillUseCase postBillUseCase) {
        return route(POST("/api/v1/bills/").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BillDto.class)
                        .flatMap(postBillUseCase::postBill)
                        .flatMap(dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(dto)
                        )
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }
}
