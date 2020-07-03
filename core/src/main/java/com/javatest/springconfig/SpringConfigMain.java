
package com.javatest.springconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringConfigMain {

    private static final Logger LOG = LoggerFactory.getLogger(SpringConfigMain.class);



    public static void main(String []args) {

        LOG.info("enter SpringConfigMain");

        // 创建bean实例，并存入 context中。要求Bean依赖的其他Bean能找到
        //ApplicationContext context = new AnnotationConfigApplicationContext(Bean1.class, Bean2.class, Bean3.class);

        // 每个ApplicationContext独立维护自己的bean实例，相互独立，互不影响
        ApplicationContext context2 = new AnnotationConfigApplicationContext(MyJavaConfig.class);
        ApplicationContext context3 = new AnnotationConfigApplicationContext(MyJavaConfig.class);

        // 通过 bean的名字获取
        ScannedBean3 scannedBean3InContext2 = (ScannedBean3) context2.getBean("bean3");
        // 通过类类型获取
        ScannedBean3 scannedBean3InContext3 = (ScannedBean3) context3.getBean(ScannedBean3.class);

        // 打印结果值不一样
        scannedBean3InContext2.setVal(8);
        scannedBean3InContext3.setVal(10);
        LOG.info("bean3InContext2 = {}, bean3InContext3 = {}", scannedBean3InContext2.getVal(), scannedBean3InContext3.getVal());

    }


}
