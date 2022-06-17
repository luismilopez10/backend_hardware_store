package com.hardwarestore.routes.provider;

import com.hardwarestore.collection.Provider;
import com.hardwarestore.dto.ProviderDto;
import com.hardwarestore.usecase.provider.PostProviderUseCase;
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
public class PostProviderRoute {

    @Bean
    @RouterOperation(
            path = "/api/v1/providers/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            beanClass = PostProviderUseCase.class,
            method = RequestMethod.POST,
            beanMethod = "postProvider",
            operation = @Operation(
                    operationId = "postProvider",
                    responses = {
                            @ApiResponse(
                                    responseCode = "201",
                                    description = "Successful operation",
                                    content = @Content(schema = @Schema(implementation = Provider.class))),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Invalid recipe details supplied")},
                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Provider.class)))
            )
    )
    public RouterFunction<ServerResponse> createProvider(PostProviderUseCase postProviderUseCase) {
        return route(POST("/api/v1/providers/").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProviderDto.class)
                        .flatMap(postProviderUseCase::postProvider)
                        .flatMap(dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(dto)
                        )
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }
}
