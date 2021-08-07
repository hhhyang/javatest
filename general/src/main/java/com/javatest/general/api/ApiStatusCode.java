package com.javatest.general.api;

import org.springframework.http.HttpStatus;


public enum ApiStatusCode {

    OK("OK", HttpStatus.OK),
    INTERNAL_ERROR("ApiStatusCode", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_ACCESS_KEY_ID("InvalidAccessKeyId", HttpStatus.FORBIDDEN),
    INVALID_REQUEST_PARAM("InvalidRequestParam", HttpStatus.BAD_REQUEST);

    String code;
    HttpStatus httpStatus;

    ApiStatusCode(final String code, final HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }


    public String getCode() {
        return this.code;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

}
