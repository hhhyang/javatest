
package com.javatest.springconfig;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
// 条件注解，只有在ConditionImpl.matches方法返回true才创建bean的实例
@Conditional(ConditionImpl.class)
public class ScannedBean2 {

    private static final Logger LOG = LoggerFactory.getLogger(ScannedBean2.class);

    @Inject
    private ScannedBean1 scannedBean1;

    public ScannedBean2() {
        LOG.info("init ScannedBean2");
    }

}
