package com.javatest.testa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
// @ComponentScan(basePackageClasses = {TestAConfiguration.class})
public class TestAConfiguration {
    public TestAConfiguration() {
        log.info("======TestAConfiguration");
    }
}
