package com.hardwarestore.routes.receipt;

import com.hardwarestore.dto.ReceiptDto;
import com.hardwarestore.usecase.receipt.PostReceiptUseCase;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PostReceiptRoute {

    @Bean
    @RouterOperation()
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
