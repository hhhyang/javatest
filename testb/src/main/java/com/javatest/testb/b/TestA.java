package com.javatest.testb.b;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestA {
    public TestA() {
        log.info("======TestBConfiguration TestA");
    }
}
