package com.javatest.framework.apiserver.utils;

import com.javatest.framework.commons.domain.vo.ApiException;
import com.javatest.framework.commons.domain.vo.ApiStatusCode;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

public class ApiAssert {

    public static void isTrue(boolean expression, ApiStatusCode code, String message) {
        if (!expression) {
            throw new ApiException(code, message);
        }
    }

    public static void notNull(@Nullable Object object, ApiStatusCode code, String message) {
        if (object == null) {
            throw new ApiException(code, message);
        }
    }

    public static void hasText(@Nullable String text, ApiStatusCode code, String message) {
        if (!StringUtils.hasText(text)) {
            throw new ApiException(code, message);
        }
    }

    public static void notEmpty(Collection collection, ApiStatusCode code, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ApiException(code, message);
        }
    }
}
