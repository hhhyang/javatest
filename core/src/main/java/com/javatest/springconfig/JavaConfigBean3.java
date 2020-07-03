package com.javatest.springconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaConfigBean3 {

    private static final Logger LOG = LoggerFactory.getLogger(JavaConfigBean3.class);



    @Setter
    @Getter
    @Accessors(chain = true)
    private XmlBean1 xmlBean1;

    public JavaConfigBean3() {
        LOG.info("init JavaConfigBean3");
    }

}
