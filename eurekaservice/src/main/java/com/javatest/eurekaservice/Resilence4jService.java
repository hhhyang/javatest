package com.javatest.eurekaservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Resilence4jService {

    public String func(String str) {
        if (str == null) {
            throw new RuntimeException();
        }
        return "success!!";
    }

    @CircuitBreaker(name = "backendA")
    public String aop(String str) {
        log.info("enter aop");
        if (str == null) {
            throw new RuntimeException();
        }
        return "success!!";
    }

    @RateLimiter(name = "backendA", fallbackMethod = "ratelimitFallback")
    public String rateLimit() {
        log.info("enter rateLimit");
        return "success";
    }

    public String ratelimitFallback(RequestNotPermitted e) {
        log.info("enter ratelimitFallback");
        return "ratelimitFallback success";
    }
}
