package com.javatest.gatewayr.config;

import com.javatest.gatewayr.controller.GatewayrHandler;
import com.javatest.gatewayr.controller.GatewayrHandler2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;


@Configuration
public class GatewayrConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(GatewayrHandler handler,
                                                         GatewayrHandler2 handler2) {
        return nest(path("/handler"),
                route(path("/hello"), handler::handlerHello)
                        .andRoute(path("/hello2"), handler::handlerHello2))
                .andNest(path("/handler2"),
                        route(path("/hello"), handler2::handlerHello)
                                .andRoute(path("/hello2"), handler2::handlerHello2));
    }

}
