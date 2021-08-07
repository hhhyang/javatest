package com.javatest.gatewayr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class GatewayrApplication {

    @Autowired
    private RouteLocator r;
    @Autowired
    private ReactiveResilience4JCircuitBreakerFactory factory;

    public static void main(String[] args) {
        SpringApplication.run(GatewayrApplication.class, args);
    }


    @Bean
    @SuppressWarnings("unchecked")
    public CommandLineRunner runner() {

        return (args) -> {

            log.info("routelocator classname: {}", r.getClass().getName());

            List<String> ids = new ArrayList<>();

            ReactiveResilience4JCircuitBreaker breaker = (ReactiveResilience4JCircuitBreaker) factory.create("backendA");

            log.info("{}", ids);

        };

    }
}
