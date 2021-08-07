package com.javatest.gatewayr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class GatewayrHandler {

    public Mono<ServerResponse> handlerHello(ServerRequest r) {
        log.info("enter handlerHello");

        return ServerResponse.ok().bodyValue("hello handlerHello");
    }

    public Mono<ServerResponse> handlerHello2(ServerRequest r) {
        log.info("enter handlerHello2");
        return ServerResponse.status(HttpStatus.BAD_GATEWAY).bodyValue("hello handlerHello");
    }

}
