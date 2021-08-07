package com.baidu.sdn.topo.analyzer.core.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class ApiSuccessResponse extends ApiResponse {

    private ApiResponseData data;

    public ApiSuccessResponse(final ApiResponseData data) {
        super(ApiStatusCode.OK.code, "success");
        this.data = data;
    }

    public ApiSuccessResponse(final String message, final ApiResponseData data) {
        super(ApiStatusCode.OK.code, message);
        this.data = data;
    }

    public <T> ApiSuccessResponse(final String message, final T data) {
        super(ApiStatusCode.OK.code, message);
        this.data = new ApiResponseData<>(data);
    }

}
