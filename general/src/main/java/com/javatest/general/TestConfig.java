package com.javatest.general;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component(value = "testConfig")
@ConfigurationProperties(prefix = "sdn.kafka")
@Data
public class TestConfig {


    private KafkaProperties device;

    private KafkaProperties link;

    private String a = "abc";

    public String append(Integer i) {
        return a + i;
    }

}
