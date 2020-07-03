package com.javatest.springconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaConfigBean1 {

    private static final Logger LOG = LoggerFactory.getLogger(JavaConfigBean1.class);

    @Setter
    @Getter
    @Accessors(chain = true)
    private int val = 0;

    public JavaConfigBean1(final int val) {
        LOG.info("init JavaConfigBean1, val = {}", val);
        this.val = val;
    }

}
