package com.javatest.framework.apiserver.api;

import com.javatest.framework.commons.domain.vo.ApiExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(assignableTypes = {ApiController.class})
public class ApiExceptionHandler extends BasicApiExceptionHandler {

    @Autowired
    public ApiExceptionHandler(HttpServletRequest httpRequest) {
        super(httpRequest);
    }

    @Override
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiExceptionResponse> handleGenericExcetion(Throwable t) {
        return super.handleGenericExcetion(t);
    }

}
