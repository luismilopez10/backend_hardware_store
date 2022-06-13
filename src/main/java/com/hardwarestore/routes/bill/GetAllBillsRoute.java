package com.hardwarestore.routes.bill;

import com.hardwarestore.collection.Bill;
import com.hardwarestore.dto.BillDto;
import com.hardwarestore.usecase.bill.GetAllBillsUseCase;
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
public class GetAllBillsRoute {

    @Bean
    @RouterOperation(
            path = "/api/v1/bills/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllBillsUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "getAllBills",
            operation = @Operation(
                    operationId = "getAllBills",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Successful operation",
                                    content = @Content(schema = @Schema(implementation = Bill.class)))
                    }
            )
    )
    public RouterFunction<ServerResponse> getAllBills(GetAllBillsUseCase getAllBillsUseCase) {
        return route(GET("/api/v1/bills/"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(getAllBillsUseCase.getAllBills(), BillDto.class))
        );
    }
}
