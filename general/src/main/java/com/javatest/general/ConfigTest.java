package com.javatest.general;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Configuration
public class ConfigTest {

    @Value("${test.a:}")
    private List<String> test;


}
