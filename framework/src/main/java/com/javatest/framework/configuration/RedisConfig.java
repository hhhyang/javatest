package com.javatest.framework.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

@Configuration
@Slf4j
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    private RedisProperties redisProps;

    @Autowired
    public RedisConfig(RedisProperties redisProps) {
        this.redisProps = redisProps;
    }

    @Primary
    @Bean
    public RedisConnectionFactory localRedisConnectionFactory() {
        if (redisProps.getMode() == RedisProperties.Mode.sentinel) {
            log.info("connect local redis with sentinel mode");
            return newLettuceSentinelConnectionFactory();
        } else {
            log.info("connect local redis with standalone mode");
            return newLettuceStandaloneConnectionFactory();
        }
    }

    @Bean(name = "LocalStringRedisTemplate")
    public StringRedisTemplate localStringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    private LettuceConnectionFactory newLettuceStandaloneConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisProps.getHost(),
                redisProps.getPort());
        config.setDatabase(redisProps.getDatabase());
        config.setPassword(RedisPassword.of(redisProps.getPassword()));
        return new LettuceConnectionFactory(config, getLettucePoolingClientConfiguration());
    }

    private LettuceConnectionFactory newLettuceSentinelConnectionFactory() {
        RedisSentinelConfiguration config = new RedisSentinelConfiguration(redisProps.getSentinel().getMaster(),
                redisProps.getSentinel().getNodes());
        config.setDatabase(redisProps.getDatabase());
        config.setPassword(RedisPassword.of(redisProps.getPassword()));
        return new LettuceConnectionFactory(config, getLettucePoolingClientConfiguration());
    }

    private LettucePoolingClientConfiguration getLettucePoolingClientConfiguration() {
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder;
        builder = LettucePoolingClientConfiguration.builder();
        GenericObjectPoolConfig<?> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        RedisProperties.Pool pool = redisProps.getPool();
        if (pool != null) {
            PropertyMapper propertyMapper = PropertyMapper.get();
            propertyMapper.from(pool::getMaxTotal).whenNonNull().to(genericObjectPoolConfig::setMaxTotal);
            propertyMapper.from(pool::getMaxIdle).whenNonNull().to(genericObjectPoolConfig::setMaxTotal);
            propertyMapper.from(pool::getMinIdle).whenNonNull().to(genericObjectPoolConfig::setMinIdle);
            propertyMapper.from(pool::getMaxWait).whenNonNull().to(genericObjectPoolConfig::setMaxWaitMillis);
        }

        builder.poolConfig(genericObjectPoolConfig);
        builder.commandTimeout(Duration.ofSeconds(60));
        builder.shutdownTimeout(Duration.ofMillis(100));
        return builder.build();
    }

}

