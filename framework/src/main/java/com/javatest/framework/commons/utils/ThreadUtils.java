package com.javatest.framework.commons.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


public class ThreadUtils {

    public static ThreadFactory namedThreadFactory(final String format) {
        return new ThreadFactoryBuilder().setNameFormat(format).build();
    }

    public static ExecutorService namedSingleThreadExecutor(final String format) {
        return Executors.newSingleThreadExecutor(ThreadUtils.namedThreadFactory(format));
    }

    public static ExecutorService namedSingleThreadExecutor(final ThreadFactory factory) {
        return Executors.newSingleThreadExecutor(factory);
    }

}
