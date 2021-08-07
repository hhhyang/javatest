package com.javatest.framework.commons.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ApiExceptionResponse extends ApiResponse {

    private String requestId;

    public ApiExceptionResponse(String requestId, String code, String message) {
        super(code, message);
        this.requestId = requestId;
    }

}
