package com.javatest.webflux.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class TestHandler {

    private int count = 0;

    public Mono<ServerResponse> hello(ServerRequest r) {
        return ServerResponse.ok().bodyValue("hello TestHandler");
    }

    public Mono<ServerResponse> hello2(ServerRequest r) {
        if ((count % 2) == 0) {
            log.info("hello TestHandler, mock fail, count: {}", count);
            count++;
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue("hello TestHandler fail");
        } else {
            log.info("hello TestHandler, mock success, count: {}", count);
            count++;
            return ServerResponse.ok().bodyValue("hello TestHandler success");
        }
    }

}
