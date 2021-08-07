package com.javatest.eurekaservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Call;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value = "/test")
public class Controller {

    private MeterRegistry meterRegistry;
    private final Counter counter1;
    private final Counter counter2;
    private final Counter counter3;
    private final Gauge gauge;
    private final Timer timer;
    private List<String> strs = new ArrayList<>();
    private CircuitBreakerRegistry circuitBreakerRegistry;
    private CircuitBreaker circuitBreaker;

    public Controller(MeterRegistry meterRegistry, CircuitBreakerRegistry circuitBreakerRegistry) {
        this.meterRegistry = meterRegistry;
        // 创建counter的方式1
        counter1 = Counter.builder("org.javatest.counter1").tag("a", "hello").register(meterRegistry);
        counter3 = Counter.builder("org.javatest.counter1").tag("a", "world").register(meterRegistry);
        // 创建counter的方式2
        counter2 = meterRegistry.counter("org.javatest.counter2", "x", "x1", "y", "y1");
        gauge = Gauge.builder("org.javatest.gauge", strs, List::size).register(meterRegistry);
        timer = Timer.builder("org.javatest.timer").register(meterRegistry);

        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendA");
    }

    @GetMapping(value = "/hello")
    public String hello() {
        // counter1数值增加1，org.javatest.counter1值为1
        counter1.increment();
        // counter1数值增加100，org.javatest.counter2值为100
        counter2.increment(100);
        // counter3数值增加2，org.javatest.counter1值为3
        counter3.increment(2.0);
        log.info("counter1: {}, counter2: {}, counter3: {}", counter1.count(), counter2.count(), counter3.count());
        log.info("gauge value: {}", gauge.value());
        strs.add("xx");
        log.info("gauge value: {}", gauge.value());
        timer.record(2000L, TimeUnit.SECONDS);
        timer.record(3000L, TimeUnit.SECONDS);
        meterRegistry.counter("org.javatest.counter2").increment();

        // counter2数值不变，org.javatest.counter2值为160
        meterRegistry.counter("org.javatest.counter2", "x", "x1").increment(60);
        // counter1、counter3数值不变，org.javatest.counter1值为7
        meterRegistry.counter("org.javatest.counter1", "a", "world").increment(4);
        // counter1、counter3数值不变，org.javatest.counter1值为9
        meterRegistry.counter("org.javatest.counter1", "a", "hhh").increment(2);
        log.info("counter1: {}, counter2: {}, counter3: {}", counter1.count(), counter2.count(), counter3.count());
        return "hello, world";
    }

    @GetMapping(value = "/hello2")
    public String hello2(@RequestParam String str) {

        log.info("enter hello2");
        return Try.ofSupplier(Decorators.ofSupplier(() -> {
                        if ("a".equals(str)) {
                            throw new NullPointerException("null pointer");
                        }
                        return "ok";
                    }).withCircuitBreaker(circuitBreaker).decorate())
                .recover(e -> {
                    if ( e instanceof CallNotPermittedException) {
                        return "Circuit is Open!!";
                    } else {
                        return "other exception";
                    }
                })
                .get();
    }

}
