package com.javatest.testa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestB {
    public TestB() {
        log.info("======TestAConfiguration TestB");
    }
}
