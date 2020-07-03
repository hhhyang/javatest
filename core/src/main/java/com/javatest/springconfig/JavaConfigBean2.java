package com.javatest.springconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaConfigBean2 {

    private static final Logger LOG = LoggerFactory.getLogger(JavaConfigBean2.class);



    @Setter
    @Getter
    @Accessors(chain = true)
    private ScannedBean2 scannedBean2;

    public JavaConfigBean2() {
        LOG.info("init JavaConfigBean2");
    }
}
