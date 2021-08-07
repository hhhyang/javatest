package com.javatest.general.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Slf4j
public class ApiSuccessResponse<T extends ApiResponseData> extends ApiResponse {

    private T data;

    public ApiSuccessResponse(final T data) {
        super(ApiStatusCode.OK.code, "success");
        this.data = data;
    }

    public ApiSuccessResponse(final String message, final T data) {
        super(ApiStatusCode.OK.code, message);
        this.data = data;

        log.info("hee");
    }

    public <U> ApiSuccessResponse(final String message, final U data) {
        super(ApiStatusCode.OK.code, message);
        this.data = (T) new ApiResponseData<>(data);

        log.info("ttt====");
    }

}
