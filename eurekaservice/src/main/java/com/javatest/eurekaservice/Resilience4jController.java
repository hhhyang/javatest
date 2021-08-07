package com.javatest.eurekaservice;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;

@Slf4j
@RestController
@RequestMapping("/resilience4j")
public class Resilience4jController {

    private final CircuitBreaker circuitBreaker;
    private final io.github.resilience4j.ratelimiter.RateLimiter rateLimiter;
    private final Resilence4jService service;

    public Resilience4jController(CircuitBreakerRegistry registry,
                                  RateLimiterRegistry rateLimiterRegistry,
                                  Resilence4jService service) {
        this.circuitBreaker = registry.circuitBreaker("backendA");
        rateLimiter = rateLimiterRegistry.rateLimiter("backendA");
        this.service = service;
    }

    @GetMapping("/func")
    public String func(@RequestParam(required = false) String str) {
        return Try.ofSupplier(CircuitBreaker.decorateSupplier(circuitBreaker, () -> service.func(str)))
                .recover(CallNotPermittedException.class, "Circuit is Open!!")
                .recover(RuntimeException.class, "fallback!!").get();
    }

    @GetMapping("/aop")
    public String aop(@RequestParam(required = false) String str) {
        return Try.ofSupplier(() -> service.aop(str)).recover(CallNotPermittedException.class, "Circuit is Open!!")
                .recover(RuntimeException.class, "fallback!!").get();
    }

    @GetMapping("/ratelimit")
    public String ratelimit() {
        return service.rateLimit();
    }

    @GetMapping("/ratelimit3")
    public String ratelimit3() {
        log.info("enter rateLimit3");
        return io.github.resilience4j.ratelimiter.RateLimiter.decorateSupplier(rateLimiter,
                () -> "hello ratelimit3").get();
    }

    @RateLimiter(name = "backendA", fallbackMethod = "ratelimitFallback2")
    @GetMapping("/ratelimit2")
    public String rateLimit2() {
        log.info("enter rateLimit2");
        return "success";
    }

    public String ratelimitFallback2(RequestNotPermitted e) {
        log.info("enter ratelimitFallback2");
        return "ratelimitFallback2 success";
    }

}
