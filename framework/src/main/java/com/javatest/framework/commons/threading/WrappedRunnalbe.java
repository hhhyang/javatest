package com.javatest.framework.commons.threading;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;


@Slf4j
public class WrappedRunnalbe implements Runnable {

    private Runnable runnable;

    public WrappedRunnalbe(final Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        try {
            runnable.run();
        } catch (Throwable t) {
            log.error(ExceptionUtils.getStackTrace(t));
        }
    }
}
