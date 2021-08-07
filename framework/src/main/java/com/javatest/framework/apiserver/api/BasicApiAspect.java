package com.javatest.framework.apiserver.api;

import com.javatest.framework.commons.domain.vo.ApiConstants;
import com.javatest.framework.commons.domain.vo.ApiUtils;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ApiException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
public class BasicApiAspect {

    @Getter
    @Setter
    private RateLimiter rateLimiter = null;

    public Object apiTopoAroundAdvise(final ProceedingJoinPoint jp) throws Throwable {

        boolean clearMDC = false;

        try {
            Instant start = Instant.now();

            Object target = jp.getTarget();
            Logger logger = LoggerFactory.getLogger(target.getClass());

            MethodSignature methodSignature = (MethodSignature) jp.getSignature();
            String[] pNames = methodSignature.getParameterNames();
            Object[] pValues = jp.getArgs();
            String params = formatMethodParameters(pNames, pValues);

            ServletRequestAttributes attrs = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
            HttpServletRequest httpRequest = attrs.getRequest();
            String clientRealIp = attrs != null ? ApiUtils.getClientRealIp(attrs.getRequest()) : "";

            if (httpRequest.getAttribute(ApiConstants.X_BCE_REQUEST_ID) == null) {
                // 本次请求第一次调用
                String requestId = UUID.randomUUID().toString();
                httpRequest.setAttribute(ApiConstants.X_BCE_REQUEST_ID, requestId);

                httpRequest.setAttribute(ApiConstants.TOPO_REQUEST_START, start);
                httpRequest.setAttribute(ApiConstants.TOPO_REQUEST_ENTRY, methodSignature.getName());

                clearMDC = true;
            }


            String requestId = (String) httpRequest.getAttribute(ApiConstants.X_BCE_REQUEST_ID);
            MDC.put(ApiConstants.X_BCE_REQUEST_ID, requestId);
            logger.info("clientRealIp: {}, start {}, parameters: {}", clientRealIp, methodSignature.getName(), params);

            Object result;
            if (rateLimiter == null) {
                result = jp.proceed();
            } else {
                // 开启限流
                result = RateLimiter.decorateSupplier(rateLimiter, () -> {
                    try {
                        return jp.proceed();
                    } catch (Throwable t) {
                        if (t instanceof ApiException) {
                            throw (ApiException) t;
                        } else {
                            throw new RuntimeException("run into a api exception", t);
                        }
                    }
                }).get();
            }


            HttpServletResponse httpResponse = attrs.getResponse();

            httpResponse.setHeader(ApiConstants.X_BCE_REQUEST_ID, requestId);
            String date = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
            httpResponse.setHeader(ApiConstants.X_BCE_DATE, date);
            httpResponse.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            Duration duration = Duration.between(start, Instant.now());
            logger.info("clientRealIp: {}, finish {}, duration time: {} ms",
                    clientRealIp, methodSignature.getName(), duration.toMillis());

            return result;
        } finally {
            if (clearMDC) {
                MDC.clear();
            }
        }
    }


    private String formatMethodParameters(@NonNull final String[] pNames, @NonNull final Object[] pValues) {

        int len = Math.min(pNames.length, pValues.length);
        StringBuilder sb = new StringBuilder();
        int i = len;
        while (i > 1) {
            sb.append(String.format("%s=%s,", pNames[len - i], pValues[len - i]));
            i--;
        }

        if (i == 1) {
            sb.append(String.format("%s=%s", pNames[len - i], pValues[len - i]));
        } else {
            sb.append("none");
        }

        return sb.toString();

    }

    public void setRateLimiter(RateLimiterRegistry registry) {
        if (registry != null) {
            rateLimiter = registry.find("apiTotalLimit").orElse(null);
        }
    }

}

