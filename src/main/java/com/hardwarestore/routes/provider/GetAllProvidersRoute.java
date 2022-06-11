package com.hardwarestore.routes.provider;

import com.hardwarestore.collection.Provider;
import com.hardwarestore.dto.ProviderDto;
import com.hardwarestore.usecase.provider.GetAllProvidersUseCase;
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
public class GetAllProvidersRoute {

    @Bean
    @RouterOperation(path = "/api/v1/providers/", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllProvidersUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "getAllProviders",
            operation = @Operation(
                    operationId = "getAllProviders",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation",
                                    content = @Content(schema = @Schema(implementation = Provider.class)))
                    }
            )
    )
    public RouterFunction<ServerResponse> getAllProviders(GetAllProvidersUseCase getAllProvidersUseCase) {
        return route(GET("/api/v1/providers/"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(getAllProvidersUseCase.getAllProviders(), ProviderDto.class))
        );
    }
}
