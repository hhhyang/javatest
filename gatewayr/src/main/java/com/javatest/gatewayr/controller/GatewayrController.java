package com.javatest.gatewayr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(value = "/gatewayr")
public class GatewayrController {

    @GetMapping(value = "/hello")
    public Mono<String> hello() {
        log.info("enter hello");
        return Mono.just("hello world");
    }

    @GetMapping(value = "/hello2")
    public Mono<String> hello2() {
        log.info("enter hello2");
        return Mono.just("hello world2");
    }

}
