package com.javatest.framework.apiserver.api;

import com.javatest.framework.commons.domain.vo.ApiConstants;
import com.javatest.framework.commons.domain.vo.ApiException;
import com.javatest.framework.commons.domain.vo.ApiExceptionResponse;
import com.javatest.framework.commons.domain.vo.ApiStatusCode;
import com.javatest.framework.commons.domain.vo.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class BasicApiExceptionHandler {

    private HttpServletRequest httpRequest;

    public BasicApiExceptionHandler(HttpServletRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public ResponseEntity<ApiExceptionResponse> handleGenericExcetion(Throwable t) {

        ApiExceptionResponse res = new ApiExceptionResponse();

        String requestId = (String) httpRequest.getAttribute(ApiConstants.X_BCE_REQUEST_ID);
        res.setRequestId(requestId);

        ApiStatusCode statusCode;
        if (t instanceof ApiException) {
            statusCode = ((ApiException) t).getApiStatusCode();
        } else {
            log.error("{}", ExceptionUtils.getStackTrace(t));
            statusCode = ApiStatusCode.INTERNAL_ERROR;
        }

        res.setCode(statusCode.getCode());
        res.setMessage(statusCode.getMessage() + t.getMessage());
        HttpStatus httpStatus = statusCode.getHttpStatus();

        String requestEntry = (String) httpRequest.getAttribute(ApiConstants.TOPO_REQUEST_ENTRY);
        String requestRealIp = ApiUtils.getClientRealIp(httpRequest);
        Instant start = (Instant) httpRequest.getAttribute(ApiConstants.TOPO_REQUEST_START);

        Duration duration = Duration.between(start, Instant.now());
        log.info("clientRealIp: {}, finish {}, duration time: {} ms", requestRealIp, requestEntry, duration.toMillis());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(ApiConstants.X_BCE_REQUEST_ID, requestId);
        String date = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        httpHeaders.set(ApiConstants.X_BCE_DATE, date);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<ApiExceptionResponse> responseEntity;
        responseEntity = new ResponseEntity<>(res, httpHeaders, httpStatus);

        return responseEntity;
    }

}
