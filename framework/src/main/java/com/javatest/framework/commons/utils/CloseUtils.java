package com.javatest.framework.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;


@Slf4j
public class CloseUtils {
    public static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

}
