package com.javatest.framework.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisProperties {

    private Mode mode = Mode.standalone;
    private Integer database = 0;
    private String host;
    private Integer port = 6379;
    private String password;
    private Integer timeout;

    private Pool pool;
    private Sentinel sentinel = new Sentinel();

    @Data
    public static class Pool {
        private Integer maxTotal;
        private Integer maxIdle;
        private Integer minIdle;
        private Integer maxWait;
    }

    @Data
    public static class Sentinel {
        private String master;
        private Set<String> nodes;
    }

    @AllArgsConstructor
    @Getter
    public static enum Mode {

        standalone(1, "STANDALONE"),
        sentinel(2, "SENTINEL"),
        cluster(3, "CLUSTER"),
        bdrp(3, "BDRP");

        private int id;
        private String value;

    }
}

