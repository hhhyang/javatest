package com.javatest.framework.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "resilience4j.ratelimiter")
@Data
public class Resilience4jRateLimiterProperties {

    private Map<String, RateLimiterConfig> instances = new HashMap<>();

    @Data
    public static class RateLimiterConfig {
        // 单位毫秒
        private Long timeoutDuration;
        // 单位秒
        private Long limitRefreshPeriod;
        // 请求个数
        private Integer limitForPeriod;
        private Boolean writableStackTraceEnabled;
    }
}
