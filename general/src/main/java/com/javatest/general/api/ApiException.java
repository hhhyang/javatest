package com.javatest.general.api;

import lombok.Data;


@Data
public class ApiException extends RuntimeException {

    private ApiStatusCode apiStatusCode;

    public ApiException(ApiStatusCode apiStatusCode, final String msg) {
        super(msg);
        this.apiStatusCode = apiStatusCode;
    }

}
