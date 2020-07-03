package com.javatest.configadmin;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigMain {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigMain.class);

    public static void main(String[] args) {

        Properties props = System.getProperties();

        LOG.info(props.getProperty("user.dir"));
        // LOG.info(props.getProperty(System.getProperty("user.dir")));

        ConfigMain m = new ConfigMain();

        LOG.info(Thread.currentThread().getContextClassLoader().getResource("configadmin.properties").toString());
        LOG.info(m.getClass().getClassLoader().getResource("configadmin.properties").getFile());
        LOG.info(m.getClass().getClassLoader().getResource("configadmin.properties").getHost());
        LOG.info(m.getClass().getClassLoader().getResource("configadmin.properties").getPath());

        ConfigManagerProvider provider = new ConfigManagerProvider();

        provider.init();

        provider.register("configadmin.properties", Config.class);


        LOG.info("TEST_LONG: {}", Config.TEST_LONG);

    }

}
