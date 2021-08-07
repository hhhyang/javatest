package com.javatest.framework.commons.domain.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class ApiSuccessResponse<T extends ApiResponseData> extends ApiResponse {

    private T data;

    public ApiSuccessResponse(final T data) {
        super(ApiStatusCode.OK.getCode(), "success");
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    public <U> ApiSuccessResponse(final U data) {
        super(ApiStatusCode.OK.getCode(), "success");
        this.data = (T) new ApiResponseData<>(data);
    }

    public ApiSuccessResponse(final String message, final T data) {
        super(ApiStatusCode.OK.getCode(), message);
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    public <U> ApiSuccessResponse(final String message, final U data) {
        super(ApiStatusCode.OK.getCode(), message);
        this.data = (T) new ApiResponseData<>(data);
    }

}
