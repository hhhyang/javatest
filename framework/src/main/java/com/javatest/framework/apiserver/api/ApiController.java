package com.javatest.framework.apiserver.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v3", produces = "application/json")
@Slf4j
@Tag(name = "ApiController")
public class ApiController {
}
