
package com.javatest.springconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
// 主bean
@Primary
// signleton prototype request session
// ConfigurableBeanFactory 和 WebApplicationContext 定义了常量
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ScannedBean1 {

    private static final Logger LOG = LoggerFactory.getLogger(ScannedBean1.class);

    // 先实例化bean，然后DI注入
    @Autowired
    private ScannedBean2 scannedBean2;

    public ScannedBean1() {
        LOG.info("init ScannedBean1");
        LOG.info("ScannedBean1: {}", scannedBean2);
    }


}