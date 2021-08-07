package com.javatest.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class GatewayTestGatewayFilterFactory extends AbstractGatewayFilterFactory<GatewayTestGatewayFilterFactory.Config> {

    public GatewayTestGatewayFilterFactory() {
        super(Config.class);
        log.info("Loaded GatewayFilterFactory [Authorize]");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("enabled");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!config.isEnabled()) {
                log.info("GatewayTestGatewayFilter is not enabaled");
                return chain.filter(exchange);
            }

            log.info("GatewayTestGatewayFilter is enabaled, pre process");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("GatewayTestGatewayFilter is enabaled, post process");;
            }));
        };
    }

    public static class Config {
        // 控制是否开启认证
        private boolean enabled;

        public Config() {}

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = Boolean.parseBoolean(enabled);
        }
    }

}
