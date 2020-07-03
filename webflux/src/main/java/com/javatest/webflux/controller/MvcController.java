package com.javatest.webflux.controller;

import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class MvcController {

    @GetMapping("/mvchello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/mvchello2")
    public List<String> hello2() {
        List<String> s = Lists.newArrayList("hello", "hellos");
        return s;
    }

}
