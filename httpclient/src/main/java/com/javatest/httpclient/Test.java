package com.javatest.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

// @Component
@Slf4j
public class Test implements BeanPostProcessor, SmartInitializingSingleton, ApplicationContextAware {

    // 所有的bean处理完后执行这个方法
    @Override
    public void afterSingletonsInstantiated() {
        log.error("====do afterSingletonsInstantiated");
    }

    // 每一个spring bean都会执行这个方法
    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // log.error("do postProcessBeforeInitialization, beanName: {}", bean);
        return null;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // log.error("do postProcessAfterInitialization, beanName: {}", bean);
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.error("applicationContext class: {}", applicationContext.getClass().getCanonicalName());
    }
}
