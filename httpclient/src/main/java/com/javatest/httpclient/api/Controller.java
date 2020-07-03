package com.javatest.httpclient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/", produces = "application/json")
public class Controller {

    @GetMapping("/user/{id}")
    public String user(@PathVariable String id) {
        log.error("hello user {}", id);
        return "hello user " + id;
    }

    @GetMapping("/user/getByUserName")
    public String getByUserName() {
        log.error("getByUserName");
        return "hello user " + "abc";
    }

}
