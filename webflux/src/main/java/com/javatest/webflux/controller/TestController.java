package com.javatest.webflux.controller;

import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class TestController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("hello " + this.getClass().getSimpleName());
    }

    @GetMapping("/hellos")
    public Flux<String> hellos() {
        List<String> s = Lists.newArrayList("hello", "hellos");
        return Flux.fromIterable(s);
    }

    @GetMapping("/hello2")
    public Mono<List<String>> hello2() {
        List<String> s = Lists.newArrayList("hello", "hellos");
        return Mono.just(s);
    }

}
