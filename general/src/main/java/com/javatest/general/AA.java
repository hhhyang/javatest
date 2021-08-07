package com.javatest.general;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AA {

    private AB ab;
    private AC ac;

    @Autowired
    public AA(AB ab, @Autowired(required = false) AC ac) {
        this.ab = ab;
        this.ac = ac;
        log.info("ab: {}, ac: {}", ab, ac);
    }

}
