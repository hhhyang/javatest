package com.javatest.guice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EagerInstance {

    private static final Logger LOG = LoggerFactory.getLogger(EagerInstance.class);

    public EagerInstance() {
        LOG.info("construct EagerInstance");
    }


}
