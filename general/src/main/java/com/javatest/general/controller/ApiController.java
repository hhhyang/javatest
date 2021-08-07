package com.javatest.general.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(value = "/test", produces = "application/json")
@Slf4j
public class ApiController {


    @PostMapping(value = "/a")
    public Dto test(@Valid @RequestBody(required = false) Body body, Errors errors) {
        // log.info("dto: {}, x: {}", dto, x);
        log.info("====body: {}", body);
        if (errors.hasErrors()) {
            log.info("count: {}", errors.getErrorCount());
        }
        return new Dto();
    }

    private void tt(@Size(min = 3) String a) {
        log.info("{}", a);
    }

}
