package com.javatest.springconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
// 导入其他 JavaConfig 配置类中定义的bean
@Import(MyJavaConfig.class)
// 导入xml文件中定义的bean
@ImportResource("classpath:springconfig/springconfig.xml")
public class ConbineJavaConfig {
}
