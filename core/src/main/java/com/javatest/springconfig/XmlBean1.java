
package com.javatest.springconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlBean1 {

    private static final Logger LOG = LoggerFactory.getLogger(XmlBean1.class);

    private ScannedBean1 scannedBean1;

    public XmlBean1() {
        LOG.info("init XmlBean1");
    }

    public XmlBean1(final ScannedBean1 bean1) {
        LOG.info("init XmlBean1 with bean1");
    }

}
