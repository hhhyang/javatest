package com.javatest.webflux;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.util.Random;
import java.util.function.Function;

@Slf4j
@SpringBootApplication
public class WebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}


	@Bean
	@SuppressWarnings("unchecked")
	public CommandLineRunner runner() {

		return (args) -> {
			Function<Flux<String>, Flux<String>> logUserInfo = (stream) -> {
				if (new Random().nextBoolean()) {
					return stream.doOnNext(e -> log.info("A user: {}", e));
				} else {
					return stream.doOnNext(e -> log.info("B user: {}", e));
				}
			};

			// Flux<String> publisher = Flux.just("1", "2").transformDeferred(logUserInfo);
			Flux<String> publisher = Flux.just("1", "2").transform(logUserInfo);
			publisher.subscribe();
			publisher.subscribe();

		};

	}

	public void exam1() {
		log.info("thread: {}", Thread.currentThread().getName());
		Flux.range(1, 4)
				.map(x -> x + 1)
				.doOnEach(x -> {log.info("---{} thread: {}", x, Thread.currentThread().getName());})
				.publishOn(Schedulers.newParallel("pub"))
				.map(x -> x * 100)
				.doOnEach(x -> {log.info("***{} thread: {}", x, Thread.currentThread().getName());})
				//.subscribeOn(Schedulers.newParallel("sub"))
				.subscribe(x -> {log.info("==={} thread: {}", x, Thread.currentThread().getName());},
						e -> {},
						() -> {log.info("===thread: {}", Thread.currentThread().getName());});

		log.info("thread: {}", Thread.currentThread().getName());

	}

}
