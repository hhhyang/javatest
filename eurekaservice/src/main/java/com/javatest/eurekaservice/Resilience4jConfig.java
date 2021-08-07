package com.javatest.eurekaservice;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Map;


@Configuration
@Slf4j
@EnableConfigurationProperties(Resilience4jRateLimiterProperties.class)
public class Resilience4jConfig {

    @Bean
    public RateLimiterRegistry anotherRateLimiterRegistry(Resilience4jRateLimiterProperties rateLimiterProps) {

        // 创建Registry
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.ofDefaults();
        PropertyMapper propertyMapper = PropertyMapper.get();
        Map<String, Resilience4jRateLimiterProperties.RateLimiterConfig> configMap = rateLimiterProps.getInstances();
        log.info("=====size: {}", configMap.size());
        for (String name : configMap.keySet()) {
            Resilience4jRateLimiterProperties.RateLimiterConfig config = configMap.get(name);
            log.info("=====name: {}, value: {}", name, config);

            // 根据配置创建 RateLimiter
            RateLimiterConfig.Builder builder = RateLimiterConfig.custom();
            propertyMapper.from(config::getLimitRefreshPeriod)
                    .whenNonNull().to(v -> builder.limitRefreshPeriod(Duration.ofSeconds(v)));
            propertyMapper.from(config::getLimitForPeriod)
                    .whenNonNull().to(builder::limitForPeriod);
            propertyMapper.from(config::getTimeoutDuration)
                    .whenNonNull().to(v -> builder.timeoutDuration(Duration.ofMillis(v)));
            propertyMapper.from(config::getWritableStackTraceEnabled)
                    .whenNonNull().to(builder::writableStackTraceEnabled);

            rateLimiterRegistry.rateLimiter(name, builder.build());
        }
        log.info("=====size: {}", rateLimiterRegistry.getAllRateLimiters().size());
        return rateLimiterRegistry;
    }

}

