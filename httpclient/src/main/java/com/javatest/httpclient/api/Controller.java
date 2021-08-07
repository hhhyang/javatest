package com.javatest.httpclient.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "/", produces = "application/json")
public class Controller {

    @GetMapping("/user/getByUserName")
    @Operation(parameters = {
            @Parameter(in= ParameterIn.QUERY, name="aa", required = false, schema = @Schema(type="string"))
    },
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "bodyddd", required = false,
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type="string"))}))
    public String getByUserName(
            @Parameter(description = "xxxxx", required = false)
            @RequestParam() Integer r,
            @RequestParam(required = false) Map<String, String> mapParam,
            @RequestBody(required = false) String body
            ) {
        log.error("getByUserName");

        return "hello user " + "abc" + r;
    }

}
