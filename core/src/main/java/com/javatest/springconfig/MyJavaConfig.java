
package com.javatest.springconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// 添加了@ComponentScan，加载MyJavaConfig时，会到指定的扫描路径下查找所有的bean组件注解，默认路径是MyJavaConfig的类路径
@ComponentScan

//自定扫描路径
//@ComponentScan(basePackages = {"com.javatest.springconfig", "com.javatest.mysql"},
//        basePackageClasses = {ScannedBean3.class})
@Configuration
public class MyJavaConfig {

    public static final Logger LOG = LoggerFactory.getLogger(MyJavaConfig.class);




    public MyJavaConfig() {
        LOG.info("init MyJavaConfig");
    }


    @Bean(name = "config_bean1")
    public JavaConfigBean1 getJavaConfigBean1() {
        return new JavaConfigBean1(4);
    }

    @Autowired
    @Bean
    // 可以通过JVM参数、环境变量或web.xml的<context-param> 或 <servlet>的<init-param>
    // 设置spring.profiles.active 或spring.profiles.default值来指定生效的profile
    @Profile("dev")
    public JavaConfigBean2 getJavaConfigBean2(final ScannedBean2 bean2) {

        JavaConfigBean2 javaConfigBean2 = new JavaConfigBean2();
        javaConfigBean2.setScannedBean2(bean2);

        return javaConfigBean2;
    }


    @Autowired
    @Bean
    @Profile("test")
    public JavaConfigBean3 getJavaConfigBean3(final XmlBean1 xmlBean1) {

        JavaConfigBean3 javaConfigBean3 = new JavaConfigBean3();
        javaConfigBean3.setXmlBean1(xmlBean1);

        return javaConfigBean3;
    }

}
