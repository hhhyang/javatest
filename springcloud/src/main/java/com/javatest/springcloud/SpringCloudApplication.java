package com.javatest.springcloud;

import com.google.common.collect.Streams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class SpringCloudApplication {

    @Autowired
    private FeignClientTest test;

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudApplication.class, args);
    }

    @Bean
    @SuppressWarnings("unchecked")
    public CommandLineRunner runner() {

        return (args) -> {
            Instant i = Instant.now();
            i.toEpochMilli();
            List<String> s = Arrays.asList("a", "b", "c");
            List<String> o = Optional.ofNullable(s).map(Collection::stream).orElse(Stream.empty())
                    .map(v -> {
                        if (v.equals("b")) {
                            return null;
                        } else {
                            return v;
                        }
                    })
                    .collect(Collectors.toList());
            log.info("{}", o.size());


        };

    }
}
