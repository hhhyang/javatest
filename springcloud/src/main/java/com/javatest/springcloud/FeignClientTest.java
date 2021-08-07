package com.javatest.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-test")
@RequestMapping(value = "/test")
public interface FeignClientTest {

    @GetMapping(value = "/hello")
    String hello();

    @GetMapping(value = "/hello2?para1=mgmtIp")
    String hello2(@RequestParam String para2);

}
