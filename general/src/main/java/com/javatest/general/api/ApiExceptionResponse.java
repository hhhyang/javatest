package com.baidu.sdn.topo.analyzer.core.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Data
@NoArgsConstructor
public class ApiExceptionResponse extends ApiResponse {

    private String requestId;

    public ApiExceptionResponse(String requestId, String code, String message) {
        super(code, message);
        this.requestId = requestId;
    }

}
