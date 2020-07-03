
package com.javatest.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedPoolThreadFactory implements ThreadFactory {

    private static final String defaultName = "named".intern();

    private static final ConcurrentMap<String, AtomicInteger> poolNumberMap = new ConcurrentHashMap<>();

    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public NamedPoolThreadFactory(String name) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();

        String iname = (name == null || name.isEmpty()) ? defaultName : name;

        AtomicInteger poolNumber;
        if (poolNumberMap.containsKey(iname)) {
            poolNumber = poolNumberMap.get(iname);
        } else {
            poolNumber = new AtomicInteger(1);
            poolNumberMap.put(iname, poolNumber);
        }

        namePrefix = iname + "-pool-" + poolNumber.getAndIncrement() + "-thread-";

    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }


}
