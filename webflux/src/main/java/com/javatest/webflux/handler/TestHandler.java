package com.javatest.webflux.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TestHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().bodyValue("hello TestHandler");
    }

}
