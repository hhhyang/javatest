package com.javatest.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
@Slf4j
public class WebclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebclientApplication.class, args);
	}

	@Bean
	public CommandLineRunner testRunner() {
		return args -> {

			Mono<String> res = WebClient.create()
					.get()
					.uri("http://localhost/hello")
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.bodyToMono(String.class);

			log.error("response: {}", res.block());

		};
	}


}
