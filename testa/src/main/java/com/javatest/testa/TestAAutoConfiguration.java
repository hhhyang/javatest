package com.javatest.testa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import(TestAConfiguration.class)
@ComponentScan(basePackageClasses = {TestAConfiguration.class})
public class TestAAutoConfiguration {

    public TestAAutoConfiguration() {
        log.info("======TestAAutoConfiguration");
    }
}
