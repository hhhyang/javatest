package com.javatest.framework.model.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "sdn")
@Data
public class KafkaConfig {

    private Map<String, KafkaProperties> kafka = new HashMap<>();

}
