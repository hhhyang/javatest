package com.javatest.webflux.router;

import com.javatest.webflux.handler.TestHandler;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class RouterConfig {

    @Bean
    @RouterOperation(beanClass = TestHandler.class, beanMethod = "hello")
    public RouterFunction<ServerResponse> monoRouterFunction(TestHandler testHandler){
        return RouterFunctions.route(GET("/api/user").and(accept(MediaType.APPLICATION_JSON)),testHandler::hello);
    }

}
