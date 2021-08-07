package com.javatest.testa.a;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestA {
    public TestA() {
        log.info("======TestAConfiguration a.TestA");
    }
}
