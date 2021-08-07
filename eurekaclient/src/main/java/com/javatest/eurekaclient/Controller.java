package com.javatest.eurekaclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/test")
public class Controller {

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello, world";
    }

    @GetMapping(value = "/hello2")
    public String hello2(@RequestParam String para1,
                         @RequestParam String para2) {

        log.info("para1: {}, para2: {}", para1, para2);

        return "hello2";
    }

    @GetMapping(value = "/hello3")
    public Abc hello3() {

        Abc abc = new Abc("xxx", "bbb");
        log.info("abc: {}", abc);

        return abc;
    }

}
