package com.baidu.sdn.topo.analyzer.core.domain.vo;

import lombok.Data;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Data
public class ApiException extends RuntimeException {

    private ApiStatusCode apiStatusCode;

    public ApiException(ApiStatusCode apiStatusCode, final String msg) {
        super(msg);
        this.apiStatusCode = apiStatusCode;
    }

}
